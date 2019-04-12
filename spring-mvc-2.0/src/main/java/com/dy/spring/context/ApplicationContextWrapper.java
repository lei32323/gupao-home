package com.dy.spring.context;

/**
 * 解耦  观察者模式
 * 有个定时任务用来监听谁实现了该方法，会进行注入上下文
 */
public interface ApplicationContextWrapper {

   void setApplicationContext(ApplicationContext applicationContext);
}
