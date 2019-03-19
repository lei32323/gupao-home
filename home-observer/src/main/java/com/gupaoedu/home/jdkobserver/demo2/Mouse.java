package com.gupaoedu.home.jdkobserver.demo2;

import java.util.Date;
import java.util.Observable;

public class Mouse  extends Observable {

    public void mobile(){
        Event event = new Event();
        event.setSource(this);
        event.setTime(new Date());
        event.setMsg("鼠标移动了一下");

        //通知发生更改，
        super.setChanged();
        //通知监听者
        super.notifyObservers(event);
    }

    public void up(){
        Event event = new Event();
        event.setSource(this);
        event.setTime(new Date());
        event.setMsg("鼠标抬起一下");

        //通知发生更改，
        super.setChanged();
        //通知监听者
        super.notifyObservers(event);
    }
    public void down(){
        Event event = new Event();
        event.setSource(this);
        event.setTime(new Date());
        event.setMsg("鼠标按了一下");

        //通知发生更改，
        super.setChanged();
        //通知监听者
        super.notifyObservers(event);
    }

}
