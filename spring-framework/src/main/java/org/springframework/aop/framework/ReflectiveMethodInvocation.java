package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

public class ReflectiveMethodInvocation implements MethodInvocation {

    protected Object proxy;

    //被织入的实例
    protected Object target;

    //被织入的方法
    protected Method method;

    //被注入的方法的参数
    protected Object[] arguments;

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    private Class<?> targetClass;

    //当前method对应的 advisor
    protected List<?> interceptorsAndDynamicMethodMatchers;

    //当前advisor调用的位置
    private int currentInterceptorIndex = -1;


    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<?> interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArgs() {
        return this.arguments;
    }


    @Override
    public Object proceed() throws Throwable {
        if (currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            //说明没有 拦截器 ，直接执行
            return this.method.invoke(this.target, this.arguments);
        } else {
            //说明有其他的拦截器
            Object interceptor = this.interceptorsAndDynamicMethodMatchers.get(++currentInterceptorIndex);
            if (interceptor instanceof MethodInterceptor) {
                MethodInterceptor mi = (MethodInterceptor) interceptor;
                return mi.invoke(this);
            }
        }
        return proceed();
    }
}
