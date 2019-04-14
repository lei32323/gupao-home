package com.dy.spring.beans.factory;

public interface BeanFactory {

    /**
     * 获取bean
     * @return
     */
    Object getBean(Class<?> clazz);

    /**
     * 获取bean
     * @return
     */
    Object getBean(String className);

}
