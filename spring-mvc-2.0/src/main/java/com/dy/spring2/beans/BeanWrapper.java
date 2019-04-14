package com.dy.spring2.beans;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 17:36
 * @Description:
 */
public class BeanWrapper {

    //类型
    private Class<?> wrapperInstanceType;


    //实体
    private Object wrapperInstance;

    public BeanWrapper(Object instance) {
        this.wrapperInstance  = instance;
        this.wrapperInstanceType = instance.getClass();
    }


    public Class<?> getWrapperInstanceType() {
        return wrapperInstanceType;
    }

    public void setWrapperInstanceType(Class<?> wrapperInstanceType) {
        this.wrapperInstanceType = wrapperInstanceType;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }
}
