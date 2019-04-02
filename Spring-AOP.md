# Spring DI

`org.springframework.beans.factory.support.SimpleInstantiationStrategy#instantiate(org.springframework.beans.factory.support.RootBeanDefinition, java.lang.String, org.springframework.beans.factory.BeanFactory, java.lang.Object, java.lang.reflect.Method, java.lang.Object...)`  获取当前正在执行factoryMethod  反射获得实体

`org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean` 对bean进行封装



`org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyPropertyValues` 属性注入



`org.springframework.beans.factory.support.BeanDefinitionValueResolver#resolveValueIfNecessary` 解析依赖注入



`org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessBeforeInstantiation` 创建代理对象的



`org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#targetSourcedBeans` 存放原始对象的集合





beanWrapper 实体的包装类型

PropertyValue 具体的包装属性的对象









具体的集合类型

`org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#singletonObjects` 存放单例对象的集合

`org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#dependentBeanMap` 存放依赖bean的集合

`org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#dependenciesForBeanMap` 存放依赖bean的集合

`org.springframework.beans.factory.support.FactoryBeanRegistrySupport#factoryBeanObjectCache` 存放factoryBean对象的缓存集合

`org.springframework.beans.factory.support.AbstractBeanFactory#mergedBeanDefinitions`   从bean名称映射到合并的RootBeanDefinition。

`org.springframework.beans.factory.support.AbstractBeanFactory#alreadyCreated` 已经创建的集合





AOP

`org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean` 真正创建Bean的方法

`org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean` 填充属性

`org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization` 创建proxy

`org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy` 创建proxy的Factory 创建jdk 或者cglib的代理

`org.springframework.aop.framework.DefaultAdvisorChainFactory#getInterceptorsAndDynamicInterceptionAdvice`  获取advisor链

`org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry#DefaultAdvisorAdapterRegistry` advisor 的方法Interceptor

`org.springframework.aop.framework.ReflectiveMethodInvocation#proceed` 执行切入点对应的通知方法



`BeanPostProcessor`  初始化操作前，初始化操作后  有一些实现的子类

​	aop切面编程的注册通知适配器，Bean对象的数据校验，Bean继承属性，方法的合并

`org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry`  负责拦截器的适配和注册过程













BeanFactory 和 FactoryBean的区别？

都是接口 

区别在于 BeanFactory 是一个 管理bean的工厂，也就是Ioc  

他的方法有getBean() 等等

具体的实现类：xmlBeanFactory ,AbstractAutowireCapableBeanFactory(主要做DI的，很重要),AnnotionConfigApplicationContext  ,ClassPathXmlApplicationContext,DefaultListableBeanFactory



FactoryBean 是一个 工厂bean， 他也是beanFactory创建出来的，spring就实现了70多个factoryBean

他的方法有getObject() 获取当前Bean对象  

getObjectType 获取当前Bean对象的类型

isSingleton 是否是单例的bean

获取FactoryBeand的时候 名称前面要加上&符号

具体的实现类：AbstractSingletonProxyFactoryBean,ListFactoryBean





![spring-aop](J:\gupaoedu\gupao-home\uml\spring-aop.png)

## 解析：

**创建代理器**

+ 从DI开始，发现`AbstractAutowireCapableBeanFactory#doCreateBean` 是获取bean的方法->

+ 通过aop的面向切面的特性，所以只要找到bean初始化的地方，查看`AbstractAutowireCapableBeanFactory#initializeBean`方法->
  + 设置bean的相关属性，如名称，类加载器，所属容器等信息
  + 初始化对象之前做的事情  `applyBeanPostProcessorsBeforeInitialization`
  + 初始化对象 `invokeInitMethods`
  + 初始化对象之后做的事情 `applyBeanPostProcessorsAfterInitialization`
+ 设置后置处理器 初始化对象之后做的事情 `org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization` ->
  + 设置后置处理器的初始化方法
+ 先从缓存中获取 `org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization` ->
  + 当后置处理器不存在的时候 包装传入的bean
+ `org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#wrapIfNecessary`  包装bean->
  + 判断是否可以代理bean
  + 获取通告advice 集合
  + 进入创建代理对象的方法
+ `org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#createProxy`  创建代理对象->
  + 创建`proxyFactory`
  + 设置Advisor
  + 设置目标资源
  + 创建代理对象
+ `org.springframework.aop.framework.ProxyFactory#getProxy(java.lang.ClassLoader)`  获取apoProxy ->
+ `org.springframework.aop.framework.ProxyCreatorSupport#createAopProxy` 获取创建代理对象的factory ->
+ `org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy`  创建代理对象的factory ->
  + 判断需要代理的类是否有接口
    + 有接口返回jdk proxyFactory
    + 不是的话返回cglib ProxyFactory
  + 默认走jdk proxyFactory
+ `org.springframework.aop.framework.JdkDynamicAopProxy#getProxy(java.lang.ClassLoader)`  真正创建代理对象的方法(只看jdk的)->
  + 获取代理对象的接口
  + 设置equals和hashcode方法
  + 通过Proxy创建代理类

**调用具体的方法**

+ `org.springframework.aop.framework.JdkDynamicAopProxy#invoke` 代理对象的回调函数 ->

  > 因为`JdkDynamicAopProxy` 实现了`InvocationHandler` ，所以只要找到当前类的invoke就可以了

  + 获取目标对象
  + 获取advise的链信息 `org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry` **类实现,并且保存到缓存**
    + 包括
      + `MethodBeforeAdviceAdapter`   之前的操作
      + `AfterReturningAdviceAdapter` 之后的操作
      + `ThrowsAdviceAdapter`  异常的操作
    + 其他的拦截器
  + 循环切入点
  + 找到符合切入点的方法
  + 符合的话保存到集合

**发起通知**

+ `org.springframework.aop.framework.ReflectiveMethodInvocation#proceed` 反射执行调用























