package com.dy.spring.beans.factory.support;

import com.dy.spring.beans.config.BeanDefinition;
import com.dy.spring.context.support.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DefaultListableBeanFactory extends AbstractApplicationContext {

    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

}
