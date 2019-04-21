package org.springframework.aop2;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:46
 * @Description:
 */
public interface AopProxyFactory {

    AopProxy createAopProxy(AdvisedSupport config) throws Exception;
}
