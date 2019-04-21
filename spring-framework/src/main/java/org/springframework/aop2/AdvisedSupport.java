package org.springframework.aop2;

import org.springframework.aop2.Advisor;
import org.springframework.aop2.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop2.adapter.AspectJAfterThrowingAdvice;
import org.springframework.aop2.adapter.MethodBeforeAdviceInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 08:45
 * @Description:
 */
public class AdvisedSupport {

    private ProxyConfig config;

    //原始对象
    private Object targetSource;

    //原始类型
    private Class<?> targetClass;

    private Pattern pointCutClassPattern;

    //保存method 和 拦截器的关系
    private Map<Method, List<Advisor>> methodCache;

    public AdvisedSupport(ProxyConfig config) {
        this.config = config;
        methodCache= new HashMap<>();
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
        //设置class的时候，解析这个class 的method，是否满足aop的增强规则
        parse();
    }

    private void parse() {
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile(".*" + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1) + ".*");
        try {

            //保存织入的函数
            Map<String, Method> aspectMethods = new HashMap<>();

            //获取织入的函数
            Class<?> aspectClass = Class.forName(config.getAspectClass());

            for (Method aspectMethod : aspectClass.getDeclaredMethods()) {
                aspectMethods.put(aspectMethod.getName(), aspectMethod);
            }

            //获取当前的类型的method
            Method[] targetMethods = this.getTargetClass().getDeclaredMethods();
            for (Method m : targetMethods) {
                //由于正则写的是.* 所以 任何情况都可以
                String targetMethodToString = m.toString();
                //正则匹配
                if (this.pointCutClassPattern.matcher(targetMethodToString).matches()) {
                    //说明符合条件
                    //因为是拦截器执行是有序的，所以对集合进行排序
                    List<Advisor> advisors = new LinkedList<>();
                    //before
                    if (config.getAspectBefore() != null && !"".equals(config.getAspectBefore())) {
                        //织入的方法 和 织入的对象
                        advisors.add(new MethodBeforeAdviceInterceptor(aspectMethods.get(config.getAspectBefore()), aspectClass.newInstance()));
                    }
                    //after
                    if (config.getAspectAfter() != null && !"".equals(config.getAspectAfter())) {
                        advisors.add(new AfterReturningAdviceInterceptor(aspectMethods.get(config.getAspectAfter()), aspectClass.newInstance()));
                    }
                    //around
                    if (config.getAspectAround() != null && !"".equals(config.getAspectAround())) {

                    }
                    //throw
                    if (config.getAspectAfterThrow() != null && !"".equals(config.getAspectAfterThrow())) {
                        advisors.add(new AspectJAfterThrowingAdvice(aspectMethods.get(config.getAspectAfterThrow()), aspectClass.newInstance()));
                    }
                    //缓存起来
                    methodCache.put(m, advisors);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean pointCutMatch() {
        return this.pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    //获取拦截器
    public List<Advisor> getInterceptorsAndDynamicInterceptionAdvice(Method method) throws NoSuchMethodException {
        List<Advisor> advisors = this.methodCache.get(method);
        //如果不存在话。拿着method再次去缓存中查询
        if (advisors == null) {
            //当这个method实例不存在时候，然后根据name和参数找到对应的method
            //然后根据method再去查找
            Method m = this.targetClass.getMethod(method.getName(), method.getParameterTypes());
            //拿着m去查询
            advisors = this.methodCache.get(m);
        }
        return advisors;

    }

}
