package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.JoinPoint;

import java.lang.reflect.Method;

//后置拦截器
public class AfterReturningAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {

    private JoinPoint joinPoint;

    public AfterReturningAdviceInterceptor(Method aspectJAdviceMethod, Object aspectJAdviceSource) {
        super(aspectJAdviceMethod, aspectJAdviceSource);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        joinPoint = mi;
        //先执行下一个链
        Object result = mi.proceed();
        //执行织入的函数
        afterReturning(result, mi.getMethod(), mi.getArgs(), mi.getThis());
        //返回原始函数的结果
        return result;
    }

    private void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        //执行织入的函数
        super.invokeAdviceMethod(joinPoint,returnValue,null);
    }
}