package com.gupaoedu.home.demo.service;

import com.gupaoedu.home.mvcframework.annotation.DyService;

@DyService
public class UserService implements IUserService{


    @Override
    public String hello() {
        return "hello";
    }
}
