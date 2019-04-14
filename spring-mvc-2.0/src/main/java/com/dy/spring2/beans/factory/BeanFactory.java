package com.dy.spring2.beans.factory;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 15:28
 * @Description:
 */
public interface BeanFactory {

    Object getBean(Class<?> beanClass);

}
