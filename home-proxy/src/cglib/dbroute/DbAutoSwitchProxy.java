package cglib.dbroute;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ݿ��ֶ��л�
 *  1.���ɴ�����Ч������Ҫ����3���ļ�)��(��Ϊʹ�õ���AMS���д��)
 *  2.ִ�д�����Ч�ʿ졣��Ϊ�и�fastClass,�����Եķ�������������ͨ��index�����в��ҷ�����Ч�ʺܿ�
 *  3.ʹ�õ����½��� �̳���Ҫ������࣬��д������
 */
public class DbAutoSwitchProxy implements MethodInterceptor {


    public Object getInstance(Class<?> clazz){

        //�����������
        Enhancer enhancer = new Enhancer();
        //���ݸ���
        enhancer.setSuperclass(clazz);
        //���ûص�����
        enhancer.setCallback(this);
        Object o = enhancer.create();
        return o;

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        //�жϴ�������
        Order order = (Order)objects[0];
        Date createTime = order.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        DataSourceEntity.setDataSource(simpleDateFormat.format(createTime));
        Object o1 = methodProxy.invokeSuper(o, objects);

        return o1;
    }
}
