package org.springframework.test;

import org.springframework.aop.aspectj.JoinPoint;

public class LogAspectj {

    public void before(JoinPoint joinPoint) {
        System.out.println("============方法开始");
    }

    public void after(JoinPoint joinPoint) {
        System.out.println("============方法结束");
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable tx) {
        System.out.println("出现异常:" + tx.getCause());

    }
}
