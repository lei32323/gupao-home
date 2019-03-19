package com.gupaoedu.home.login.v1;

/**
 * 新增其他登陆方式
 */
public class UserServiceAdapter extends UserService{

    //通过QQ
    //根据用户名和密码登录
    public void loginByQQ(String qq){
        System.out.println("通过QQ登录成功");
    }

    //通过微信
    public void loginByWX(String wx){
        System.out.println("通过微信登录成功");
    }

}
