# 观察者模式
要点： 回调函数

## jdk 实现
+ 观察者 需要 实现`Observer`接口，重写update方法，用来监听对象的更改

+ 被观察者 需要 继承`Observable`类，每次更改信息，需要设置更改状态`super.setChanged();`，并且通知观察者`super.notifyObservers();`

+ `java.util.Observable`

 + `private Vector<Observer> obs;` 存放 观察者的集合，使用Vector，防止线程安全问题

 + `addObserver(Observer o)` 添加观察者 ，把观察者添加到obs集合中
 
 + `deleteObserver(Observer o)` 删除观察者
 
 + `void notifyObservers()` 通知所有观察者
    
    + `notifyObservers(Object arg)` 通知 
            
      + `changed` 判断状态是否是更改过的，不是就return掉
      + 获取到通知对象，转换成数组 
      + 通过反射调用Update方法 

属于广播形式。只要订阅了。。都会收到。


## guava 实现
实现GPer社区提问通知的业务场景