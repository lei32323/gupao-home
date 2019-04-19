package org.springframework.aop;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;

public interface AopProxyFactory {

    AopProxy createAopProxy(AdvisedSupport config) throws Exception;
}
