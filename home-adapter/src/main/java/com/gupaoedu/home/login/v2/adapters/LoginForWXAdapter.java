package com.gupaoedu.home.login.v2.adapters;

public class LoginForWXAdapter implements ILoginAdapter {
    @Override
    public boolean support(Object apadter) {
        return apadter instanceof LoginForWXAdapter;
    }

    @Override
    public void login(String id) {
        System.out.println("微信登陆成功");
    }
}
