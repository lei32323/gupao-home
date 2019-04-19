package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;

import java.lang.reflect.Method;

//异常捕获拦截器
public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {

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
            return mi.proceed();
        } catch (Exception e) {
            if (shouldInvokeOnThrowing(e)) {
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