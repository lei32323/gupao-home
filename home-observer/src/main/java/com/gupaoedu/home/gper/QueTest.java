package com.gupaoedu.home.gper;

import com.google.common.eventbus.EventBus;
import com.gupaoedu.home.gper.stu.Student;
import com.gupaoedu.home.gper.stu.ZhangSanStudent;
import com.gupaoedu.home.gper.teacher.MicTeacher;
import com.gupaoedu.home.gper.teacher.TomTeacher;

public class QueTest {


    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        //添加老师
        eventBus.register(new TomTeacher());
        eventBus.register(new MicTeacher());

        QueEvent queEvent = new QueEvent();//问题
        Student student = new ZhangSanStudent();//提问学生
        queEvent.setStu(student);
        queEvent.setMsg("1+1 = ?");

        //发布问题
        eventBus.post(queEvent);

    }

}
