package cglib.dbroute;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库字段切换
 *
 */
public class DbAutoSwitchProxy implements MethodInterceptor {


    public Object getInstance(Class<?> clazz){

        //创建代理对象
        Enhancer enhancer = new Enhancer();
        //传递父类
        enhancer.setSuperclass(clazz);
        //设置回调方法
        enhancer.setCallback(this);
        Object o = enhancer.create();
        return o;

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        //判断传入的入参
        Order order = (Order)objects[0];
        Date createTime = order.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        DataSourceEntity.setDataSource(simpleDateFormat.format(createTime));
        Object o1 = methodProxy.invokeSuper(o, objects);

        return o1;
    }
}
