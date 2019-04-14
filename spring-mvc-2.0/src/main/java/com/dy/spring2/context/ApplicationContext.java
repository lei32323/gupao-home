package com.dy.spring2.context;

import com.dy.spring.annotation.Autowired;
import com.dy.spring2.beans.BeanWrapper;
import com.dy.spring2.beans.config.BeanDefinition;
import com.dy.spring2.beans.factory.BeanDefinitionReader;
import com.dy.spring2.beans.factory.BeanFactory;
import com.dy.spring2.beans.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Auther: wanglei
 * @Date: 2019/4/14 15:32
 * @Description:
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    private String configLocation;

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();

    private Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, BeanWrapper>();

    public ApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        refresh();
    }


    @Override
    //刷新上下文
    public void refresh() {
        //定位
        BeanDefinitionReader beanDefinitionReader = new BeanDefinitionReader(this.configLocation);
        //加载
        List<BeanDefinition> beanDefinitions = beanDefinitionReader.loadBeanDefinition();
        //注册
        registerBeanDefinition(beanDefinitions);

    }

    private void registerBeanDefinition(List<BeanDefinition> beanDefinitions) {
        if (super.beanDefinitionMap.isEmpty()) {
            return;
        }
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryClassName())) {
                continue;
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryClassName(), beanDefinition);
        }


    }

    @Override
    public Object getBean(Class<?> beanClass) {

        return doCreateBean(beanClass.getName());
    }

    private Object doCreateBean(String beanName) {
        //根据beanName去缓存中获取
        BeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        //1.根据bean的信息进行创建实例
        Object instance = instantiateBean(beanDefinition);

        //2.对创建的实体进行包装
        BeanWrapper beanWrapper = new BeanWrapper(instance);

        //3.进行属性注入
        //?为什么先创建实体，然后再注入呢？
        populetaBean(beanName, beanDefinition, beanWrapper);

        return beanWrapper.getWrapperInstance();

    }

    private void populetaBean(String beanName, BeanDefinition beanDefinition, BeanWrapper beanWrapper) {
        try {

            String className = beanDefinition.getClassName();
            Class<?> aClass = Class.forName(className);
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Autowired autowired = declaredField.getAnnotation(Autowired.class);
                if (autowired== null) {
                    continue;
                }
                //获取属性值
                String name = autowired.value();
                if("".equals(name)){
                    name = declaredField.getType().getName();
                }
                //保存
                if(singletonObjects.get(name)==null){
                    continue;
                }
                Object o = singletonObjects.get(name);
                //依赖注入
                declaredField.set(beanWrapper.getWrapperInstance(),o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object instantiateBean(BeanDefinition beanDefinition) {
        //默认是单例的。
        Object instance = null;
        //如果存在了
        if (singletonObjects.containsKey(beanDefinition.getClassName())) {
            instance = singletonObjects.get(beanDefinition.getClassName());
        } else {
            try {
                //没有存在的话
                String className = beanDefinition.getClassName();
                instance = Class.forName(className).newInstance();
                //保存
                singletonObjects.put(beanDefinition.getFactoryClassName(), instance);
                singletonObjects.put(className, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;

    }
}
