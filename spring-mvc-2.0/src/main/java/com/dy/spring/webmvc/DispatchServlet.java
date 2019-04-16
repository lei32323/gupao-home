package com.dy.spring.webmvc;

import com.dy.spring.annotation.Controller;
import com.dy.spring.annotation.RequestMapping;
import com.dy.spring.context.ApplicationContext;
import com.dy.spring.webmvc.servlet.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.runtime.linker.JavaAdapterServices.getHandle;

public class DispatchServlet extends HttpServlet {

    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private ApplicationContext applicationContext;

    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private Map<HandlerMapping, HandlerAdapater> HandlerAdapaters = new ConcurrentHashMap<HandlerMapping, HandlerAdapater>();

    //存放viewResolve
    private List<ViewResolver> viewResolvers = new CopyOnWriteArrayList<ViewResolver>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDisPatch(req, resp);
    }

    private void doDisPatch(HttpServletRequest req, HttpServletResponse resp) {
        //获取到请求的地址，去匹配到信息
        HandlerMapping handle = getHandle(req);

        HandlerAdapater ha = getHandleAdapate(handle);
        ModelAndView modelAndView = ha.handle(req, resp, ha);

        try {
            processDispatchResult(req, resp, modelAndView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, ModelAndView modelAndView) throws Exception {

        for (ViewResolver viewResolver : this.viewResolvers) {
            View view = viewResolver.resolveViewName(modelAndView.getView(), null);
            view.render(modelAndView.getModel(), req, resp);
        }

    }

    private HandlerAdapater getHandleAdapate(HandlerMapping handle) {
        HandlerAdapater handlerAdapater = this.HandlerAdapaters.get(handle);
        return handlerAdapater;
    }

    private HandlerMapping getHandle(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "");
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }
        return null;
    }

    @Override
    public void init() throws ServletException {

        //获取applicationContext
        applicationContext = new ApplicationContext(getServletConfig().getInitParameter(CONTEXT_CONFIG_LOCATION));

        //初始化9大组件
        initStrategies(applicationContext);
    }

    private void initStrategies(ApplicationContext applicationContext) {
        //Url和method进行关联
        initHandlerMappings(applicationContext);
        //对参数进行适配
        initHandlerAdapters(applicationContext);
        //视图进行处理
        initViewResolvers(applicationContext);

    }

    private void initViewResolvers(ApplicationContext applicationContext) {
        //获取模板对应的文件路径
        String templateRoot = applicationContext.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        //获取文件
        File templateRootFile = new File(templateRootPath);
        File[] files = templateRootFile.listFiles();
        for (File file : files) {
            viewResolvers.add(new ViewResolver(file.getName()));
        }
    }

    private void initHandlerAdapters(ApplicationContext applicationContext) {
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            //每一个请求对应一个参数适配器
            this.HandlerAdapaters.put(handlerMapping, new HandlerAdapater());
        }
    }

    private void initHandlerMappings(ApplicationContext applicationContext) {
        //获取beanDefinition的name集合
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //获取一个bean
            Object bean = applicationContext.getBean(beanDefinitionName);
            //获取类型
            Class<?> clazz = bean.getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                //获取url
                RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);

                String value = annotation.value();
                if ("".equals(value)) {
                    baseUrl = value;
                }
            }

            //获取method
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                //判断是否有requestMapping
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                //是否有requestMapping标签。
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);

                String value = annotation.value();
                //拼接url
                String regex = ("/" + baseUrl + "/" + value.replaceAll("\\*", "*")).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                //保存到handlerMapping中
                HandlerMapping handlerMapping = new HandlerMapping(pattern, bean, method);
                handlerMappings.add(handlerMapping);
            }

        }


    }


}
