package com.wl.user.service;

import com.wl.user.api.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public String login(String name) {
        return "登录成功:" + name;
    }
}
