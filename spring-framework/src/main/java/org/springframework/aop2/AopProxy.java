package org.springframework.aop2;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 09:46
 * @Description:
 */
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
