package com.dy.spring.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 19:14
 * @Description:
 */
public class HandlerMapping {

    private Pattern pattern;

    private Object controller;

    private Method method;

    public HandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.controller = controller;
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }



    public Object getController() {
        return controller;
    }



    public Method getMethod() {
        return method;
    }

}
