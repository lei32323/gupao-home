package com.gupaoedu.home.jdkobserver.demo1;


import java.util.Observable;
import java.util.Observer;

/**
 * 被观察者  老师
 */
public class Teacher extends Observable {

    //数据
    private String data;

    public void live(){
        //开始直播
        System.out.println("开始直播");
        data =  "开始直播";
        //把对象设置成改成状态
        super.setChanged();
        //通知观察者
        super.notifyObservers();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
