package com.gupaoedu.home.guavaoberser;

import com.google.common.eventbus.EventBus;

import java.util.Date;

public class MouseTest {

    public static void main(String[] args) {
        EventBus  eventBus = new EventBus();
        //注册监听者
        eventBus.register(new MouseListener());
        Event event = new Event();
        event.setSource("aaa");
        event.setMsg("新增");
        event.setTime(new Date());
        //发送信息
        eventBus.post(event);
    }

}
