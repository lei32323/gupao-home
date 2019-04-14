package com.dy.spring2.beans.config;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 15:29
 * @Description:
 */
public class BeanDefinition {

    private String className;

    private String factoryClassName;

    private boolean isLazyInit = false;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFactoryClassName() {
        return factoryClassName;
    }

    public void setFactoryClassName(String factoryClassName) {
        this.factoryClassName = factoryClassName;
    }

    public boolean isLazyInit() {
        return isLazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        isLazyInit = lazyInit;
    }
}
