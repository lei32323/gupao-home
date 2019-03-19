package com.gupaoedu.home.login.v2;

/**
 * 只给扩展使用的adapter
 */
public interface ILoginForThirdAdapter {

    //QQ登陆
    void loginForQQ(String qq);

    //微信
    void loginForWX(String wx);

    //手机登陆
    void loginForPhone(String phone);

    /**
     * 注册并且登陆
     * @param username
     * @param password
     */
    void registerLogn(String username,String password);


}
