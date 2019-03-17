## 模板模式
说明:
    模板方法模式在一个方法中定义一个算法的骨架，而将一些步骤的实现延迟到子类中。
    模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中某些步骤的具体实现。

注意：
+ 保护抽象类中定义算法顺序的方法不被子类修改
+ 分离可变不可变的部分，让子类自己去实现可变部分
+ 让算法的具体实现对子类开放，对其它类关闭 

常见的模板模式 
+ Arrays.sort参数中`Comparator`这个则为模板模式
+ `HttpServlet` 的父类`GenericServlet` 就是模板模式，通过抽象`service(ServletRequest var1, ServletResponse var2) `方法给子类。
+ 'AbstractApplicationContext' 也是模板模式 其中`refresh()`里面的`postProcessBeanFactory()`和`onRefresh()`都是钩子方法
