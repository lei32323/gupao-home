package com.gupaoedu.home.jdkobserver.demo2;

public class MouseTest {

    public static void main(String[] args) {
        Mouse mouse  = new Mouse();
        //添加监听
        mouse.addObserver(new MouseListener());
        mouse.mobile();
        mouse.down();
        mouse.up();


    }

}
