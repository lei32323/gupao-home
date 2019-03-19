package com.gupaoedu.home.jdkobserver.demo2;

import java.util.Observable;
import java.util.Observer;

public class MouseListener implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("监听到鼠标事件");
        Event event = (Event)arg;
        System.out.println(event);
    }
}
