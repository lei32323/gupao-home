package com.wl.user;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/spring/application.xml");

        classPathXmlApplicationContext.start();

        System.out.println("Provider started.");

        System.in.read();

    }

}
