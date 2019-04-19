package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;

import java.lang.reflect.Method;

//后置拦截器
public class AfterReturningAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {


    public AfterReturningAdviceInterceptor(Method aspectJAdviceMethod, Object aspectJAdviceSource) {
        super(aspectJAdviceMethod, aspectJAdviceSource);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object result = super.invokeAdviceMethod(mi, null, null);
        afterReturning(result, mi.getMethod(), mi.getArgs(), mi.getThis());
        return mi.proceed();
    }

    private void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        method.invoke(target, args);
    }
}