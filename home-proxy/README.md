## 静态代理 
需要代理的对象必须包含在代理对象中。
+ 需要代理的类一定要有接口
+ 代理类要实现InvocationHandler接口
### 案例：
    张三的妈妈需要给儿子小明介绍对象。
    1.小明需要提出要求
    2.妈妈需要拿着小明的要求去无色对象

### 案例 ：
    在分库分表的业务下，需要动态切换数据库
    1.每一年创建的订单放到一个库中

![动态数据源切换](uml/TIM截图20190311200023.png)


## 动态代理


## 问题?
为什么JDK动态代理中要求目标类实现的接口数量不能超过65535个？

查了jdk的文档后了解：
~~~
The resulting proxy class must not exceed any limits imposed on classes by the virtual machine. For example, the VM may limit the number of interfaces that a class may implement to 65535; in that case, the size of the interfaces array must not exceed 65535.
~~~
翻译：
生成的代理类不能超过虚拟机对类强加的任何限制，VM允许类可以实现接口的数量最大是65535个.

又查找资料了解到：
+ 由于java使用UNICODE(UCS-2)标准字符集，为16位，因此一共能表示2的16次方个字符，即65535个

+ 类或接口的直接超接口的数量被ClassFile结构的interfaces_count项的大小限制为65535。

+ Class文件中方法、字段等都需要引用CONSTANT_UTF8_info型常量来描述名称，所以CONSTANT_UTF8_info型常量的最大长度也就是Java中方法和字段名的最大长度。最大值length是65535，所以Java程序中如果定义了超过64KB英文字符的变量或方法名，将会无法编译。

+ 在java中：Java类或接口最多可以有65,535种方法。Java中构造函数的代码限制为65,535字节