package com.dy.spring.context;

import com.dy.spring.annotation.Autowired;
import com.dy.spring.annotation.Controller;
import com.dy.spring.annotation.Service;
import com.dy.spring.beans.BeanWrapper;
import com.dy.spring.beans.config.BeanDefinition;
import com.dy.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.dy.spring.beans.factory.support.DefaultListableBeanFactory;
import com.dy.spring.beans.factory.BeanFactory;
import com.dy.spring.context.support.AbstractApplicationContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {


    private String configLocation;

    private Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, BeanWrapper>();

    private Map<String, Object> singletonObjecrs = new HashMap<String, Object>();

    private AbstractBeanDefinitionReader reader ;

    public ApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        //初始化加载
        refresh();
    }

    @Override
    public void refresh() {
        //定位
        reader = new AbstractBeanDefinitionReader(this.configLocation);
        //加载
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinition();
        //注册
        registerBeanDefinition(beanDefinitions);

    }

    //注册
    private void registerBeanDefinition(List<BeanDefinition> beanDefinitions) {
        //先判断是否u才能在
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                continue;
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    @Override
    public Object getBean(Class<?> clazz) {

        return doGetBean(AbstractBeanDefinitionReader.toFirstLow(clazz.getSimpleName()));
    }

    @Override
    public Object getBean(String className) {
        return doGetBean(className);
    }


    private Object doGetBean(String beanName) {
        BeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        //初始化
        Object instantiateBean = instantiateBean(beanName, beanDefinition);

        //包装成beanWrapper
        BeanWrapper beanWrapper = new BeanWrapper(instantiateBean);

        //保存到缓存
        factoryBeanInstanceCache.put(beanName, beanWrapper);

        //注入
        populiateBean(beanName, new BeanDefinition(), beanWrapper);

        return factoryBeanInstanceCache.get(beanName).getWrapperInstance();

    }

    private void populiateBean(String beanName, BeanDefinition beanDefinition, BeanWrapper beanWrapper) {
        //
        Object wrapperInstance = beanWrapper.getWrapperInstance();

        Class<?> wrapperClass = beanWrapper.getWrapperClass();
        //获取属性
        Field[] declaredFields = wrapperClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(declaredField.getAnnotation(Autowired.class)==null){
                continue;
            }
            Autowired annotation = declaredField.getAnnotation(Autowired.class);
            String name = annotation.value();
            if("".equals(name)){
                name = AbstractBeanDefinitionReader.toFirstLow(declaredField.getType().getSimpleName());
            }
            Object o = null;
            declaredField.setAccessible(true);
            if(this.factoryBeanInstanceCache.get(name)==null){
               //重新调用getBean方法
                 doGetBean(name);
            }

            try{
                declaredField.set(wrapperInstance,this.factoryBeanInstanceCache.get(name).getWrapperInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String beanName, BeanDefinition beanDefinition) {
        //获取className
        String beanClassName = beanDefinition.getBeanClassName();
        Object instance = null;
        //判断单例是否存在 默认是单例的
        if (singletonObjecrs.containsKey(beanClassName)) {
            instance = singletonObjecrs.get(beanClassName);
        } else {
            try {
                //创建对象
                Class<?> aClass = Class.forName(beanDefinition.getBeanClassName());
                instance = aClass.newInstance();
                //保存
                //类型也当做key保存
                this.singletonObjecrs.put(beanClassName, instance);
                //beanName形式保存
                this.singletonObjecrs.put(beanName, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionSize(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }
}
