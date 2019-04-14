package com.dy.demo;

import com.dy.spring.context.ApplicationContext;

public class StudentTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext("application.properties");
        Object bean = applicationContext.getBean(StudentController.class);
        System.out.println(bean);
    }

}
