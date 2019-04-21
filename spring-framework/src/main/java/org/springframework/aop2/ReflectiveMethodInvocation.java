package org.springframework.aop2;


import java.lang.reflect.Method;
import java.util.List;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:56
 * @Description:
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    //被代理的方法
    private Method method;

    //被代理的对象
    private Object object;

    //代理对象
    private Object proxy;

    //参数
    private Object[] args;

    //被代理的对象的拦截器
    private List<Advisor> interceptors;

    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Method method, Object object, Object[] args, List<Advisor> interceptors) {
        this.method = method;
        this.object = object;
        this.proxy = proxy;
        this.args = args;
        this.interceptors = interceptors;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object getProxy() {
        return this.proxy;
    }

    @Override
    public Object getThis() {
        return this.object;
    }

    @Override
    public Object[] getArgs() {
        return this.args;
    }


    @Override
    public Object proceed() throws Throwable {
        //调用链
        if (this.currentInterceptorIndex == this.interceptors.size() - 1) {
            //说明没有拦截器
            return this.method.invoke(this.object, this.args);
        } else {
            Advisor advisor = this.interceptors.get(++this.currentInterceptorIndex);
            if (advisor instanceof MethodInterceptor) {
                MethodInterceptor mi = (MethodInterceptor) advisor;
                return mi.invoke(this);
            }
        }

        return proceed();
    }

}
