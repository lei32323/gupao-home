package org.springframework.aop2;

import java.lang.reflect.Method;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 08:58
 * @Description: 拦截器执行对象
 */
public interface MethodInvocation extends JoinPoint {

    //需要织入的函数
    Method getMethod();

    //下一个调用链
    Object proceed() throws Throwable;

}
