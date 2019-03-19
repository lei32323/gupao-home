package com.gupaoedu.home.jdkobserver.demo1;

public class ObserverTest {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setData("aaaaaaaa");
        //追加观察者
        teacher.addObserver(new Student());
        teacher.live();

    }

}
