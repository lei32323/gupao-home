package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;

import java.lang.reflect.Method;

//方法前置拦截器
public class MethodBeforeAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {

    public MethodBeforeAdviceInterceptor(Method aspectJAdviceMethod, Object aspectJAdviceSource) {
        super(aspectJAdviceMethod, aspectJAdviceSource);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //执行 织入的方法
        super.invokeAdviceMethod(mi, null, null);
        //调用下一个
        return mi.proceed();
    }

}
