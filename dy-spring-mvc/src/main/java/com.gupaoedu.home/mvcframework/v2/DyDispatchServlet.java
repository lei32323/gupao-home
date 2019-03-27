package com.gupaoedu.home.mvcframework.v2;


import com.gupaoedu.home.mvcframework.annotation.DyRequestMapping;
import com.gupaoedu.home.mvcframework.annotation.DyAutowired;
import com.gupaoedu.home.mvcframework.annotation.DyService;
import com.gupaoedu.home.mvcframework.annotation.DyController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;

/**
 * 核心控制类
 */
public class DyDispatchServlet extends HttpServlet {

    private Properties configContext = new Properties();

    private List<String> classNames = new ArrayList<String>();

    private Map<String, Object> ioc = new HashMap<String, Object>();

//    private Map<String, Method> handleMappings = new HashMap<String, Method>();

    //使用实体，遵守了单一原则。并且遵守最少知道原则
    private List<HandleMapping> handleMappings = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        dispatch(req, resp);
    }


    @Override
    public void init(ServletConfig config) throws ServletException {

        //1.定位配置文件 -> 定位到配置的信息
        doLoadConfig(config.getInitParameter("configLocaltion"));

        //2.扫描包里面的类 -> 加载配置信息对应的package
        doScanner(configContext.getProperty("com.dy.package.scan"));

        //3.添加到ioc容器 -> 添加到map
        doInstance();

        //4.属性赋值 -> 依赖注入
        doAutowired();

        //5.创建handleMapping->绑定method 和 url的关系
        doHandleMapping();

        System.out.println("初始化成功");

    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HandleMapping handleMapping = getHandleMapping(req);
            if (handleMapping == null) {
                resp.getWriter().write("404");
            }
            Map<String, String[]> parameterMap = req.getParameterMap();
            Object[] param = new Object[handleMapping.getParameterTypes().length];

            for (String key : parameterMap.keySet()) {
                //获取到位置
                Integer index = handleMapping.parameterIndex.get(key);
                String value = Arrays.toString(parameterMap.get(key)).
                        replaceAll("\\[", "").
                        replaceAll("\\]", "").replaceAll(",+", ",");
                //类型转换
                param[index] = convert(handleMapping.getParameterTypes()[index],value);
            }
            Object invoke = handleMapping.getMethod().invoke(handleMapping.getController(), param);
            if (invoke == null || invoke instanceof Void) {
                return;
            }
            resp.getWriter().write(invoke.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object convert(Class<?> type,String val){
        if(Integer.class == type){
            return Integer.valueOf(val);
        }else if(Double.class == type){
            return Double.valueOf(val);
        }
        return val;
    }

    //获取handleMappings
    private HandleMapping getHandleMapping(HttpServletRequest req) {
        if (handleMappings.isEmpty()) {
            return null;
        }
        //7.执行请求
        //1.获取Url
        //绝对路径
        String requestURI = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        requestURI = requestURI.replaceAll(contextPath, "").replaceAll("/+", "/");

        for (HandleMapping handleMapping : handleMappings) {
            if (handleMapping.getUrl().equals(requestURI)) {
                return handleMapping;
            }
        }
        return null;
    }

    private void doHandleMapping() {
        //1.找到类被注解的方法
        for (String key : ioc.keySet()) {
            Object o = ioc.get(key);

            DyRequestMapping annotation1 = o.getClass().getAnnotation(DyRequestMapping.class);
            String value = "";
            if (annotation1 != null) {
                value = annotation1.value();
            }

            Method[] methods = o.getClass().getMethods();
            for (Method method : methods) {
                DyRequestMapping annotation = method.getAnnotation(DyRequestMapping.class);
                if (annotation == null) {
                    continue;
                }
                String url = annotation.value();
                url = value + "/" + url;
                url = url.replaceAll("/+", "/");
                String simpleName = method.getDeclaringClass().getSimpleName();
                HandleMapping handleMapping = new HandleMapping(url, method, ioc.get(firstLowercase(simpleName)));
                //2.保存
                handleMappings.add(handleMapping);
            }
        }


    }

    private void doAutowired() {

        try {
            //1.循环所有ioc
            for (String key : ioc.keySet()) {
                Object o = ioc.get(key);
                //2.找到需要注入的属性
                Field[] declaredFields = o.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    DyAutowired annotation = field.getAnnotation(DyAutowired.class);
                    if (annotation == null) {
                        continue;
                    }
                    //从ioc中找到属性
                    //先按照名字找一遍
                    String name = field.getName();
                    Object findo = ioc.get(firstLowercase(name));
                    if (findo == null) {
                        //再按照类型找一遍
                        String typeName = field.getType().getSimpleName();
                        findo = ioc.get(typeName);
                        if (findo == null) {
                            throw new RuntimeException("没有找到需要注入的对象");
                        }
                    }
                    //3.进行反射注入
                    field.setAccessible(true);
                    field.set(o, findo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            //循环遍历class名称
            for (String className : classNames) {
                //1.实例化对象
                Class<?> aClass = Class.forName(className);
                String key = firstLowercase(aClass.getSimpleName());
                if (aClass.getAnnotation(DyController.class) != null) {
                    //2.判断对象是否有别名
                    DyController annotation = aClass.getAnnotation(DyController.class);
                    String value = annotation.value();
                    if (!value.equals("")) {
                        key = value;
                    }
                    //3.验证是否已经在ioc中存在
                    if (ioc.containsKey(key)) {
                        throw new RuntimeException("class " + key + " 已经存在");
                    }
                    //4.保存到Ioc中
                    Object o = aClass.newInstance();
                    ioc.put(key, o);
                } else if (aClass.getAnnotation(DyService.class) != null) {
                    DyService annotation = aClass.getAnnotation(DyService.class);
                    //1.获取到别名
                    String value = annotation.value();
                    if (!value.equals("")) {
                        key = value;
                    }
                    //2.验证是否已经存在
                    if (ioc.containsKey(key)) {
                        throw new RuntimeException("class " + key + " 已经存在");
                    }
                    ioc.put(key, aClass);
                    //3.验证是有接口
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (Class inter : interfaces) {
                        //2.验证是否已经存在
                        if (ioc.containsKey(inter.getSimpleName())) {
                            throw new RuntimeException("class " + key + " 已经存在");
                        }
                        //把接口名当做Key保存到Ioc中
                        Object o = aClass.newInstance();
                        ioc.put(inter.getSimpleName(), o);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doScanner(String scanPackage) {
        //获取到路径
        String packagePath = scanPackage.replaceAll("\\.", "/");
        //获取URL
        URL resource = this.getClass().getClassLoader().getResource("/" + packagePath);
        //获取文件
        File classPath = new File(resource.getFile());

        //循环
        File[] files = classPath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                //获取类路径
                String className = (scanPackage + "." + file.getName()).replace(".class", "");
                classNames.add(className);//保存起来
            }
        }


    }


    private void doLoadConfig(String configLocaltion) {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(configLocaltion)) {
            configContext.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //首字母小写
    private String firstLowercase(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public class HandleMapping {

        //url
        private String url;

        //方法
        private Method method;

        //controller
        private Object controller;

        //参数
        private Class<?>[] parameterTypes;

        private Map<String, Integer> parameterIndex;

        public HandleMapping(String url, Method method, Object controller) {
            this.url = url;
            this.method = method;
            this.controller = controller;
            this.parameterTypes = method.getParameterTypes();
            parameterIndex = new HashMap<String, Integer>();
            //初始化
            initParameter();
        }

        private void initParameter() {
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                String name = parameter.getName();
                parameterIndex.put(name, i);
            }
        }


        public String getUrl() {
            return url;
        }

        public Method getMethod() {
            return method;
        }

        public Class<?>[] getParameterTypes() {
            return parameterTypes;
        }

        public Map<String, Integer> getParameterIndex() {
            return parameterIndex;
        }

        public Object getController() {
            return controller;
        }
    }
}
