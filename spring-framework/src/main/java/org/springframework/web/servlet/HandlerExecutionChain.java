package org.springframework.web.servlet;

public class HandlerExecutionChain {

    private Object handler;

    public HandlerExecutionChain(Object handler) {
        this.handler = handler;
    }
}
