package com.wl.user.service;

import com.wl.user.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String hello(String name) {
        System.out.println("executor hello");
        return "hello "+name;
    }
}
