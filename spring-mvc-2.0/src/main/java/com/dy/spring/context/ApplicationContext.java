package com.dy.spring.context;

import com.dy.spring.beans.config.BeanDefinition;
import com.dy.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.dy.spring.beans.factory.support.DefaultListableBeanFactory;
import com.dy.spring.beans.factory.BeanFactory;
import com.dy.spring.context.support.AbstractApplicationContext;

import java.util.List;

public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {


    private String configLocation;

    public ApplicationContext(String configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    public void refresh() {
        //定位
        AbstractBeanDefinitionReader beanDefinitionReader = new AbstractBeanDefinitionReader(this.configLocation);
        //加载
        List<BeanDefinition> beanDefinitions = beanDefinitionReader.loadBeanDefinition();
        //注册
        registerBeanDefinition(beanDefinitions);

        System.out.println("1");
    }

    //注册
    private void registerBeanDefinition(List<BeanDefinition> beanDefinitions) {
        //先判断是否u才能在
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                continue;
            }
          super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    @Override
    public Object getBean() {


        return null;
    }
}
