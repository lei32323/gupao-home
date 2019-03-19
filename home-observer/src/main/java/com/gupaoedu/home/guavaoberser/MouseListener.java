package com.gupaoedu.home.guavaoberser;

import com.google.common.eventbus.Subscribe;
import com.gupaoedu.home.jdkobserver.demo2.Event;


public class MouseListener {

    @Subscribe
    public void event(Object e){
        System.out.println("监听到事件"+e);
    }


}
