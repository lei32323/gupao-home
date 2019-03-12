package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MeiPoProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) {
        //�൱��Proxy������Ĺ�����
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("��ʼǰ");

        Object o1 = methodProxy.invokeSuper(o, objects);

        System.out.println("��ʼ��");
        return o1;
    }
}
