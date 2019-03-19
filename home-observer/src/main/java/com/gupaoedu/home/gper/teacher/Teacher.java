package com.gupaoedu.home.gper.teacher;

import com.gupaoedu.home.gper.QueEvent;

public interface Teacher {

    /**
     * 接收消息
     */
     void receiveQue(QueEvent queEvent);

}
