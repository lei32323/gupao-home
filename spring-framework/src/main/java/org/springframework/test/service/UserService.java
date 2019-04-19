package org.springframework.test.service;

import org.springframework.annotation.Service;

@Service
public class UserService implements IUserService {

    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public String add(String name) {
        throw new RuntimeException("故意抛出来的异常");
    }
}
