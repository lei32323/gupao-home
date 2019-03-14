package cglib.dbroute;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库字段切换
 *  1.生成代理类效率慢（要生成3个文件)。(因为使用的是AMS框架写的)
 *  2.执行代理类效率快。因为有个fastClass,把所以的方法保存起来，通过index来进行查找方法，效率很快
 *  3.使用的是新建类 继承需要代理的类，重写方法。
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
