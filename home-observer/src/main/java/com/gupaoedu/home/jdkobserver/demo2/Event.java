package com.gupaoedu.home.jdkobserver.demo2;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Event {

    //事件源
    private Observable source;

    //事件内容
    private String msg;

    //事件发起的时间
    private Date time;

    public Observable getSource() {
        return source;
    }

    public void setSource(Observable source) {
        this.source = source;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }
}
