package org.springframework.aop.aspectj;

public interface JoinPoint {

    Object getThis();


    Object[] getArgs();

}
