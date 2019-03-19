package com.gupaoedu.home.guavaoberser;

import java.util.Date;
import java.util.Observable;

public class Event {

    //事件源
    private Object source;

    //事件内容
    private String msg;

    //事件发起的时间
    private Date time;

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
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
