package org.springframework.beans;

public class BeanWrapper {

    private Object wrappedInstance;

    public BeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    //类型
    public Class<?> getWrappedClass() {
        return wrappedInstance.getClass();
    }

    //实例
    public Object getWrappedInstance() {
        return wrappedInstance;
    }
}
