package com.gupaoedu.home.demo.controller;

import com.gupaoedu.home.mvcframework.annotation.DyAutowired;
import com.gupaoedu.home.mvcframework.annotation.DyController;
import com.gupaoedu.home.demo.service.IUserService;
import com.gupaoedu.home.mvcframework.annotation.DyRequestMapping;
import com.gupaoedu.home.mvcframework.annotation.DyRequestParam;

@DyController
public class UserController {

    @DyAutowired
    private IUserService userService;

    @DyRequestMapping("/hello")
    public String hello(@DyRequestParam("name") String name) {
        return "hello " + name;
    }


    @DyRequestMapping("/void")
    public void hello() {
        System.out.println("123");
    }

}
