package com.dy.spring.beans.factory;

public interface FactoryBean {

    /**
     * 获取对象
     * @return
     */
    Object getObject();

    /**
     * 获取类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否是单例的
     * @return
     */
    boolean isSingeton();

}
