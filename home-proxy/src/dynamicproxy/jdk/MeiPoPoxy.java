package dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MeiPoPoxy implements InvocationHandler {

    //������
    private Object target;

    public Object getIntance(Object target){
        this.target = target;
        Class<?> aClass = target.getClass();
        Object o = Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
        return  o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("֮ǰ");

        Object invoke = method.invoke(target, args);

        System.out.println("֮��");
        return invoke;
    }
}
