package com.gupaoedu.home.gper.teacher;

import com.google.common.eventbus.Subscribe;
import com.gupaoedu.home.gper.QueEvent;

public class MicTeacher implements Teacher {

    @Subscribe
    @Override
    public void receiveQue(QueEvent queEvent) {
        System.out.println("===========mic接收到["+queEvent.getStu().getName()+"]提问");
        System.out.println(queEvent.getMsg());
    }
}
