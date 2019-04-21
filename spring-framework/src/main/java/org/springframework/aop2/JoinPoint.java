package org.springframework.aop2;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:05
 * @Description:  切入点
 */
public interface JoinPoint {

    //代理对象
    Object getProxy();


    //需要织入的对象
    Object getThis();


    //需要植入的方法的参数
   Object[] getArgs();

}
