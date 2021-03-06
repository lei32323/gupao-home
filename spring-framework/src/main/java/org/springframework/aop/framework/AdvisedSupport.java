package org.springframework.aop.framework;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.AspectJAfterThrowingAdvice;
import org.springframework.aop.framework.adapter.AspectJAroundAdvice;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvisedSupport {

    //需要被织入的对象
    private Object targetSource;

    //需要被织入的对象类型
    private Class<?> targetClass;

    //存储method对应的adivsor集合 链路集合
    private Map<Method, List<Advisor>> methodCache;

    //保存 aop切面的正则
    private Pattern pointCutClassPattern;

    //配置文件
    private ProxyConfig config;


    public AdvisedSupport(ProxyConfig config) {
        this.config = config;
        this.methodCache = new HashMap<>();
    }

    public Object getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(Object targetSource) {
        this.targetSource = targetSource;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        //设置原始实例的类型的时候，进行初始化
        parse();
    }

    private void parse() {
        //解析
        //解析 pointCut public .* com.dy.demo..*Service..*(.*)
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile(".*" + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1)+".*");

        try {
            //获取织入的类
            String aspectClass = config.getAspectClass();
            Class<?> aspectclazz = Class.forName(aspectClass);

            //存储织入的类中的函数
            Map<String, Method> aspectMethods = new HashMap<>();
            for (Method am : aspectclazz.getDeclaredMethods()) {
                aspectMethods.put(am.getName(), am);
            }

            //遍历当前传入的类是否满足进行aop切面
            Method[] targetMethods = targetClass.getDeclaredMethods();
            for (int i = 0; i < targetMethods.length; i++) {
               Method targetMethod =  targetMethods[i];
                //获取方法的toString()
                String targetMethodString = targetMethod.toString();
                //判断是否有throws
                if (targetMethodString.contains("throws")) {
                    targetMethodString = targetMethodString.substring(0, targetMethodString.indexOf("throws")).trim();
                }

                //比较这个函数是否满足aop织入的条件
                Matcher matcher = this.pointCutClassPattern.matcher(targetMethodString);
                if (matcher.matches()) {
                    //由于有顺序的。需要需要按顺序执行
                    List<Advisor> advisors = new LinkedList<>();
                    //满足的话。设置通知器(拦截器) after  before  throwing...
                    if (config.getAspectBefore() != null && !"".equals(config.getAspectBefore())) {
                        //创建拦截器
                        advisors.add(new MethodBeforeAdviceInterceptor(aspectMethods.get(config.getAspectBefore()), aspectclazz.newInstance()));
                    }

                    if (config.getAspectAfter() != null && !"".equals(config.getAspectAfter())) {
                        advisors.add(new AfterReturningAdviceInterceptor(aspectMethods.get(config.getAspectAfter()), aspectclazz.newInstance()));
                    }

                    if (config.getAspectAround() != null && !"".equals(config.getAspectAround())) {
                        advisors.add(new AspectJAroundAdvice(aspectMethods.get(config.getAspectAround()), aspectclazz.newInstance()));
                    }

                    if (config.getAspectAfterThrow() != null && !"".equals(config.getAspectAfterThrow())) {
                        AspectJAfterThrowingAdvice aspectJAfterThrowingAdvice = new AspectJAfterThrowingAdvice(aspectMethods.get(config.getAspectAfterThrow()), aspectclazz.newInstance());
                        aspectJAfterThrowingAdvice.setDiscoveredThrowingType(config.getAspectAfterThrowingName());
                        advisors.add(aspectJAfterThrowingAdvice);
                    }

                    //缓存起来
                    methodCache.put(targetMethod, advisors);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean pointCutMatch() {
        return this.pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    //获取所有的拦截器
    public List<Advisor> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {
        //从缓存中获取
        List<Advisor> cached = this.methodCache.get(method);
        //如果传入的method 在缓存中 不存在
        if (cached == null) {
            //根据传入的mehtod的名称和参数类型 去被织入的对象中找到method
            Method m = targetClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
            //在根据m去cache获取
            cached = this.methodCache.get(m);
        }
        return cached;
    }

    public static void main(String[] args) {
        String pointCut = "public .* org.springframework.test.service..*Service..*(.*)"
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        String aaa = " *" + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1);
        System.out.println(aaa);
        Pattern pointCutClassPattern = Pattern.compile(".*org\\.springframework\\.test\\.service\\..*Service.*");
        System.out.println("public java.lang.String org.springframework.test.service.UserService.add(java.lang.String)");
        Matcher matcher = pointCutClassPattern.matcher("public java.lang.String org.springframework.test.service.UserService.add(java.lang.String)");
        System.out.println(matcher.matches());
    }

}
