package org.springframework.aop2.adapter;

import org.springframework.aop2.Advisor;
import org.springframework.aop2.AbstractAspectJAdvice;
import org.springframework.aop2.MethodInterceptor;
import org.springframework.aop2.MethodInvocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:01
 * @Description:
 */
public class AfterReturningAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor, Advisor {


    public AfterReturningAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    //链调用的方法
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //先执行原来的方法
        Object retVal = after(mi.getMethod(), mi.getThis(), mi.getArgs());

        //之后调用的
        super.invokeAspectMethod(mi, null,null);

        //调用下一个链
        return mi.proceed();
    }

    private Object after(Method method, Object targetSource, Object[] args) throws InvocationTargetException, IllegalAccessException {
       return method.invoke(targetSource, args);
    }

}
