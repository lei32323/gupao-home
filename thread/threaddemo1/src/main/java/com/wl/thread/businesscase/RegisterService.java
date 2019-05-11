package com.wl.thread.businesscase;

/**
 * 非多线程操作
 */
public class RegisterService {

    public String register(String loginName,String phone,String email){

        //插入数据库
        System.out.println("插入数据库耗时:3ms");

        //发送短信
        System.out.println("调用发送短信接口:4ms");
        //发送邮件
        System.out.println("调用发送邮件接口:5ms");


        System.out.println("注册操作共耗时3ms+4ms+5ms=12ms");

        return "success";
    }

}
