package com.gupaoedu.home.gper;

import com.gupaoedu.home.gper.stu.Student;

import java.util.Date;

/**
 * 问题的事件
 */
public class QueEvent {

    //问题日期
    private Date time;

    //提问人
    private Student stu;

    private String msg;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "QueEvent{" +
                "time=" + time +
                ", stu=" + stu +
                ", msg='" + msg + '\'' +
                '}';
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
