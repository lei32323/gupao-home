package org.springframework.aop2;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 08:57
 * @Description:
 */
public interface MethodInterceptor {

    //方法拦截器
    Object invoke(MethodInvocation invocation) throws Throwable;


}
