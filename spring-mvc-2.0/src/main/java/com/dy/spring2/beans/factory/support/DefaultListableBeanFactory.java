package com.dy.spring2.beans.factory.support;

import com.dy.spring2.beans.config.BeanDefinition;
import com.dy.spring2.context.support.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 15:32
 * @Description:
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();




}
