package org.springframework.aop.framework;

//aop代理接口
public interface AopProxy {
    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
