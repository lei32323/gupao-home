package org.springframework.aop2;

import org.springframework.aop2.AopProxyFactory;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:47
 * @Description:
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws Exception {
        if(config.getTargetClass().getInterfaces().length>0){
            return new JdkDynamicAopProxy(config);
        }
        return null;
    }
}
