package com.dy.spring.beans.config;

import lombok.Data;

@Data
public class BeanDefinition {

    /**
     * bean的类名称
     */
    private String beanClassName;

    /**
     * 是否延迟加载
     */
    private boolean isLazyInit = false;

    /**
     * 存在于工厂里面的名称
     */
    private String factoryBeanName;

}
