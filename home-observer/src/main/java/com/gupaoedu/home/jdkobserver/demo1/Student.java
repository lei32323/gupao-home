package com.gupaoedu.home.jdkobserver.demo1;

import java.util.Observable;
import java.util.Observer;

public class Student implements Observer {

    //接收老师的信息 进行操作
    @Override
    public void update(Observable o, Object arg) {
        //转换成老师对象 获取信息
        Teacher t = (Teacher)o;
        System.out.println("============收到开始直播的消息");
        System.out.println(t.getData());
        System.out.println("观看视频");
    }
}
