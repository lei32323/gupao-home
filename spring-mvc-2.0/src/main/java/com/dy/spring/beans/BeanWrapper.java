package com.dy.spring.beans;

public class BeanWrapper {

    private Object wrapperInstance;

    public BeanWrapper(Object wrapperInstance){
        this.wrapperInstance = wrapperInstance;
    }

    //获取实例
    //有可能是代理类
    public Object getWrapperInstance(){
        return this.wrapperInstance;
    }

    //获取类型
    //有可能是代理的类型
    public Class<?> getWrapperClass(){
        return this.wrapperInstance.getClass();
    }
}
