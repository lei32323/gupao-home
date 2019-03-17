package com.gupaoedu.home.login.v1;

/**
 * 原始的业务类
 */
public class UserService {

    //注册的方法
    public void register(String username,String password){
        System.out.println("注册成功");
    }

    //根据用户名和密码登录
    public void loginByUsernamePassword(String username,String password){
        System.out.println("通过用户名和密码登录成功");
    }

}
