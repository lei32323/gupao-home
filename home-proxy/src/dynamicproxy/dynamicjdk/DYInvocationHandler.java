package dynamicproxy.dynamicjdk;

import java.lang.reflect.Method;

public interface DYInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
