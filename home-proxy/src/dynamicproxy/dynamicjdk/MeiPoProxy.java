package dynamicproxy.dynamicjdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MeiPoProxy implements DYInvocationHandler {

    private Object target;

    //������Ҫ����Ķ���
    public Object getIntance(Object target){
        this.target = target;
        Class<?> aClass = target.getClass();
        Object o = DYProxy.newProxyInstance(new DYClassLoader(), aClass.getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("�Ҷ���ǰ");
        Object invoke = method.invoke(target, args);
        System.out.println("�Ҷ����");

        return invoke;
    }
}
