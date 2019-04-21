package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;

import java.lang.reflect.Method;

//异常捕获拦截器
public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {

    //捕获的类型
    private Class<?> discoveredThrowingType;

    public void setDiscoveredThrowingType(String discoveredThrowingTypeString) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(discoveredThrowingTypeString);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.discoveredThrowingType = aClass;
    }

    public AspectJAfterThrowingAdvice(Method aspectJAdviceMethod, Object aspectJAdviceSource) {
        super(aspectJAdviceMethod, aspectJAdviceSource);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            //执行链路
            return mi.proceed();
        } catch (Exception e) {
            //捕获异常
            if (shouldInvokeOnThrowing(e)) {
                //执行织入的函数
                super.invokeAdviceMethod(mi, null, e);
            }
            throw e;
        }
    }

    private boolean shouldInvokeOnThrowing(Throwable ex) {
        //判断是否是这个类型
        return discoveredThrowingType.isAssignableFrom(ex.getClass());
    }
}