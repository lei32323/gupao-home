package com.gupaoedu.home.login.v2.adapters;

public class LoginForQQAdapter implements ILoginAdapter{
    @Override
    public boolean support(Object apadter) {
        return apadter instanceof LoginForQQAdapter;
    }

    @Override
    public void login(String id) {
        System.out.println("登陆成功");
    }



}
