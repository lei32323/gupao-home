package org.aopalliance.intercept;

import org.springframework.aop.aspectj.JoinPoint;

import java.lang.reflect.Method;

public interface MethodInvocation extends JoinPoint {

    Method getMethod();


    Object proceed() throws Throwable;
}
