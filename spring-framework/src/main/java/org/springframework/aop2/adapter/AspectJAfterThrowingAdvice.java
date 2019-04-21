package org.springframework.aop2.adapter;

import org.springframework.aop2.Advisor;
import org.springframework.aop2.AbstractAspectJAdvice;
import org.springframework.aop2.MethodInterceptor;
import org.springframework.aop2.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:01
 * @Description:
 */
public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {


    public AspectJAfterThrowingAdvice(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    //链调用的方法
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        try {
            return mi.proceed();
        } catch (Exception e) {
            super.invokeAspectMethod(mi, null, e);
            throw e;
        }


    }
}
