# 面试题
## spring管理的Bean线程安全吗？
spring只是扫描你的bean通过反射进行创建对象并且保存起来，
不会对bean做任何的操作，所以说spring管理的bean和线程安全没有关系，
线程安全不安全取决于你自己。


## spring中bean在什么时候会被回收？
首先我们知道gc的回收机制是：当地址没有被引用的时候，就会被回收

在看spring中的bean有生命周期：singleton ,session,request,propertype
如果是sgingleton的话，在Ioc容器中是单例的，她会一直保存被引用的状态，所以她会随着ioc容器的销毁而被回收
session 就是在一会话后
request是一次请求后
propertype 是被使用后，就被回收了。