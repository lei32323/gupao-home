package org.springframework.aop2;

import java.lang.reflect.Method;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:26
 * @Description:
 */
public class AbstractAspectJAdvice {

    //织入的对象
    private Object aspectObject;

    //织入的函数
    private Method aspectMethod;

    public AbstractAspectJAdvice(Method aspectMethod,Object aspectObject ) {
        this.aspectObject = aspectObject;
        this.aspectMethod = aspectMethod;
    }

    /**
     * 执行织入的方法
     *
     * @param joinPoint 切入点
     * @param args      参数
     * @param tx        异常
     * @return
     */
    protected Object invokeAspectMethod(JoinPoint joinPoint, Object[] args, Throwable tx) throws Exception {
        Class<?>[] parameterTypes = this.aspectMethod.getParameterTypes();
        //验证织入的方法是否有参数
        if (parameterTypes == null && parameterTypes.length == 0) {
            //说明没有参数，直接调用
            return this.aspectMethod.invoke(this.aspectObject);
        } else {
            //说明有参数
            Object[] values = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                //判断参数的类型 ，进行赋值
                if (parameterType == JoinPoint.class) {
                    values[i] = joinPoint;
                } else if (parameterType == tx.getClass()) {
                    values[i] = tx;
                } else if (parameterType == Object.class) {
                    values[i] = args;
                }
            }
            //执行带参的方法
            return this.aspectMethod.invoke(this.aspectObject, values);
        }
    }
}
