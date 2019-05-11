package com.wl.thread.businesscase;

public class RegisterServiceThread {

    public String register(String loginName,String phone,String email){

        //插入数据库
        System.out.println("插入数据库耗时:3ms");

        new Thread(()->{
            //发送短信
            System.out.println("调用发送短信接口:4ms");
        },"send-messages").start();

        new Thread(()->{
            //发送邮件
            System.out.println("调用发送邮件接口:5ms");
        },"send-email").start();

        System.out.println("注册操作共耗时3ms");

        return "success";
    }


}
