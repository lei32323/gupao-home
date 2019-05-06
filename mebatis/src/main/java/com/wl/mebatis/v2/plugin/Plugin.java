package com.wl.mebatis.v2.plugin;

import com.wl.mebatis.v2.annotation.Intercepts;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Plugin implements InvocationHandler {

    private Object target;  //被代理对象

    private Interceptor interceptor; //拦截器


    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target.getClass().isAnnotationPresent(Intercepts.class)) {
            //拦截method
            if (method.getName().equals(interceptor.getClass().getAnnotation(Intercepts.class).value())) {
                return interceptor.intercept(new Invocation(target, method, args));
            }
        }
        return method.invoke(target, method, args);
    }
}
