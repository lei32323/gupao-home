package dynamicproxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxyTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, FileNotFoundException {

        //��ȡ����
        MeiPoPoxy meiPoPoxy = new MeiPoPoxy();
        //��Ҫ��������,��ȡ���������
        Object intance = meiPoPoxy.getIntance(new Zhangsan());

        Method findLove = intance.getClass().getDeclaredMethod("findLove", null);
        findLove.invoke(intance);


//        //��ӡ���������
//        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IProsen.class});
//        try(FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class")){
//            fileOutputStream.write(bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

}
