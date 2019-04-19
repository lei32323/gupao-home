package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport config;

    public JdkDynamicAopProxy(AdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object getProxy() {
        //使用当前对象
        return getProxy(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, config.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取所有的拦截器
        List<Advisor> chain = config.getInterceptorsAndDynamicInterceptionAdvice(method, this.config.getTargetClass());

        MethodInvocation methodInvocation = new ReflectiveMethodInvocation(proxy, this.config.getTargetSource(), method, args, this.config.getTargetClass(), chain);


        return methodInvocation.proceed();

    }
}
