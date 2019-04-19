package org.springframework.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice {

    //织入的方法
    protected Method aspectJAdviceMethod;

    //织入的对象
    //spring中 是直接从bean工厂获取
    protected Object aspectJAdviceSource;

    public AbstractAspectJAdvice(Method aspectJAdviceMethod, Object aspectJAdviceSource) {
        this.aspectJAdviceMethod = aspectJAdviceMethod;
        this.aspectJAdviceSource = aspectJAdviceSource;
    }

    protected Object invokeAdviceMethod(JoinPoint jp, Object returnValue, Throwable ex) throws Throwable {
        //根据传入的进行处理
        Class<?>[] parameterTypes = aspectJAdviceMethod.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
            //说明没有参数
            return this.aspectJAdviceMethod.invoke(this.aspectJAdviceSource);
        } else {
            //说明有参数
            Object[] values = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == JoinPoint.class) {
                    values[i] = jp;
                } else if (parameterTypes[i] == Throwable.class) {
                    values[i] = ex;
                } else if (parameterTypes[i] == Object.class) {
                    values[i] = returnValue;
                }else if(parameterTypes[i] == ProceedingJoinPoint.class){
                    //保存当前对象进去
                    MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = new MethodInvocationProceedingJoinPoint((MethodInvocation) jp);
                    values[i] = methodInvocationProceedingJoinPoint;
                }
            }
            //反射执行
            return this.aspectJAdviceMethod.invoke(this.aspectJAdviceSource, values);
        }
    }
}
