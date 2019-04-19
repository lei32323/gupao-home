package org.springframework.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class MethodInvocationProceedingJoinPoint implements JoinPoint, MethodInvocation {

    private MethodInvocation methodInvocation;

    @Override
    public Object getThis() {
        return methodInvocation.getThis();
    }

    @Override
    public Object[] getArgs() {
        return this.methodInvocation.getArgs();
    }

    public MethodInvocationProceedingJoinPoint(MethodInvocation methodInvocation) {
        this.methodInvocation = methodInvocation;
    }


    @Override
    public Method getMethod() {
        return methodInvocation.getMethod();
    }

    @Override
    public Object proceed() throws Throwable {
        //执行当前对象
        return this.methodInvocation.proceed();
//        return this.methodInvocation.invocableClone().proceed();
    }


}
