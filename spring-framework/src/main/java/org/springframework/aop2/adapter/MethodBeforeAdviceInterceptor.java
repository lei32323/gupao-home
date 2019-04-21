package org.springframework.aop2.adapter;

import org.springframework.aop2.AbstractAspectJAdvice;
import org.springframework.aop2.Advisor;
import org.springframework.aop2.MethodInterceptor;
import org.springframework.aop2.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:00
 * @Description: 方法执行调用的方法
 */
public class MethodBeforeAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {


    public MethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    //链调用的方法
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //执行织入的方法
        super.invokeAspectMethod(mi, null, null);
        //执行下一个链路
        return mi.proceed();
    }


}
