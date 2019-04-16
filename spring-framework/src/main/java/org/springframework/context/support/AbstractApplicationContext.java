package org.springframework.context.support;

import org.springframework.annotation.Autowired;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    private String configLocation;

    //解析BeanDefinitionReader
    private BeanDefinitionReader reader;

    //存放到单例对象的集合
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    //存放早期暴露的单例集合
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    //存放循环依赖的集合的  spring中使用的是factory 这里直接使用object来存储
    private final Map<String, Object> singletonFactories = new ConcurrentHashMap<>();

    public AbstractApplicationContext(String configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    public void refresh() {
        //刷新上下文
        //定位
        reader = new BeanDefinitionReader(this.configLocation);
        //加载
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinition();
        //注册
        reginsterBeanDefinition(beanDefinitions);

    }

    protected void reginsterBeanDefinition(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            reader.getRegistry().registerBeanDefinition(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    @Override
    public Object getBean(String name) {
        return doGetBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) doGetBean(name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {

        return (T) doGetBean(reader.toFirstLow(requiredType.getSimpleName()));
    }

    protected Object doGetBean(String beanName) {
        //1.从缓存中获取
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance == null) {
            //说明没有需要创建
            //2.存在的话保存
            sharedInstance = doCreateBean(beanName, null, null);
            singletonObjects.put(beanName, sharedInstance);
        }
        return sharedInstance;

    }

    protected Object doCreateBean(String beanName, BeanDefinition mbd, Object[] args) {
        Object newInstance = null;
        try {
            //从缓存中获取beanDeifnition
            mbd = this.reader.getRegistry().getBeanDefinition(beanName);
            //创建Bean对象返回
            String beanClassName = mbd.getBeanClassName();
            Class<?> clazz = Class.forName(beanClassName);

            //这里使用默认构造函数
            newInstance = clazz.newInstance();

            BeanWrapper beanWrapper = new BeanWrapper(newInstance);

            //保存到三级缓存中
            this.singletonFactories.put(beanName, newInstance);

            //依赖注入
            populateBean(beanName, mbd, beanWrapper);

            //初始化

            //通知
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    //属性注入
    protected void populateBean(String beanName, BeanDefinition mbd, BeanWrapper bw) {
        //直接根据属性进行注入
        Class<?> clazz = bw.getWrappedClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Autowired autowired = declaredField.getAnnotation(Autowired.class);
            //是否必须要注入
            boolean required = autowired.required();

            String fieldName = declaredField.getName();
            Object bean = getBean(fieldName);
            if (required && bean == null) {
                throw new RuntimeException(declaredField.getName() + " 对应的实体不存在");
            }
            //反射注入
            try {
                declaredField.setAccessible(true);
                declaredField.set(bw.getWrappedInstance(), bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //从缓存中获取
    private Object getSingleton(String beanName) {
        //先从一级缓存中获取
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null) {
            //从二级缓存获取
            singletonObject = earlySingletonObjects.get(beanName);
        }
        if (singletonObject == null) {
            singletonObject = this.singletonFactories.get(beanName);
        } else {
            //如果在三级缓存中，那么就保存到二级缓存，并且三级缓存删除
            earlySingletonObjects.put(beanName, singletonObject);
            singletonFactories.remove(beanName);
        }
        return singletonObject;
    }
}
