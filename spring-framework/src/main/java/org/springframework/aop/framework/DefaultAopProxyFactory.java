package org.springframework.aop.framework;

import org.springframework.aop.AopProxyFactory;

public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws Exception {
        //是否有接口
        if (config.getTargetClass().getInterfaces().length > 0) {
            //使用jdk的
            return new JdkDynamicAopProxy(config);
        } else {
            //否则使用cglib的
            return new ObjenesisCglibAopProxy(config);
        }

    }
}
