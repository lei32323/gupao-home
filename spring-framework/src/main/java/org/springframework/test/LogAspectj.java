package org.springframework.test;

import org.springframework.aop.aspectj.JoinPoint;
import org.springframework.aop.aspectj.ProceedingJoinPoint;

import javax.jws.Oneway;
import java.lang.reflect.Method;

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

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("之前的操作");
        //调用方法
        System.out.println(joinPoint.proceed());
        System.out.println("之后的操作");
    }
}
