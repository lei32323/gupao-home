package com.wl;

import com.wl.user.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application.xml");

        UserService bean = classPathXmlApplicationContext.getBean(UserService.class);

        System.out.println(bean.hello("wanglei"));

    }
}
