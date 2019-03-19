package com.gupaoedu.home.login.v2.adapters;

public interface ILoginAdapter {

    //验证是否是当前类
    boolean support(Object apadter);


    //登陆的方法
    void login(String id);
}
