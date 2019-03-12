package dynamicproxy.dynamicjdk;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DYProxyTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MeiPoProxy meiPoProxy = new MeiPoProxy();
        Object intance = meiPoProxy.getIntance(new Zhangsan());

        Method findLove = intance.getClass().getDeclaredMethod("findLove", null);
        findLove.invoke(intance);
    }

}
