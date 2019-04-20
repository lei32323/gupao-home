package org.springframework.aop.aspectj;

public interface ProceedingJoinPoint extends JoinPoint {

     Object proceed() throws Throwable;

     Object proceed(Object[] args) throws Throwable;

}
