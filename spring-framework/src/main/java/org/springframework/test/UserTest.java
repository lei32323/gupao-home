package org.springframework.test;

import org.springframework.context.ApplicationContext;

public class UserTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext("classpath:application.properties");
        UserController bean = applicationContext.getBean(UserController.class);
        System.out.println(bean);
    }

}
