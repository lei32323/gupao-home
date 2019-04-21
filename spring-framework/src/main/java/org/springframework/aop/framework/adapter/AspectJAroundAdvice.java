package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

import java.lang.reflect.Method;

public class AspectJAroundAdvice extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {

    public AspectJAroundAdvice(Method aspectJAdviceMethod, Object aspectJAdviceSource) {
        super(aspectJAdviceMethod, aspectJAdviceSource);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //之后调用
        return super.invokeAdviceMethod(mi, null, null);
    }

}
