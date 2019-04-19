package org.springframework.aop.framework;

public class ObjenesisCglibAopProxy implements AopProxy {

    private AdvisedSupport config;

    public ObjenesisCglibAopProxy(AdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
