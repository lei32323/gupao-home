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
        before(mi.getMethod(), mi.getArgs(), mi.getThis());
        //调用当前方法执行
        super.invokeAdviceMethod(mi, null, null);
        //调用链
        return mi.proceed();
    }

    //调用before方法
    private void before(Method method, Object[] args, Object target) throws Throwable {
        //直接执行
        method.invoke(target, args);
    }
}
