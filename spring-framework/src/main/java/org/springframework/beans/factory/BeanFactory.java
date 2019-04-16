package org.springframework.beans.factory;

public interface BeanFactory {

    //根据bean名称获取bean
    Object getBean(String name) ;

    //根据bean名称+类型返回
    <T> T getBean(String name, Class<T> requiredType);

    //根据class类型获取bean
    <T> T getBean(Class<T> requiredType);
}
