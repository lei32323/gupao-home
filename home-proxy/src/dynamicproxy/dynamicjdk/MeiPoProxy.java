package dynamicproxy.dynamicjdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MeiPoProxy implements DYInvocationHandler {

    private Object target;

    //设置需要代理的对象
    public Object getIntance(Object target){
        this.target = target;
        Class<?> aClass = target.getClass();
        Object o = DYProxy.newProxyInstance(new DYClassLoader(), aClass.getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("找对象前");
        Object invoke = method.invoke(target, args);
        System.out.println("找对象后");

        return invoke;
    }
}
