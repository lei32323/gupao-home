package org.springframework.web.servlet;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

public class HandlerMapping {

    private String url;

    private Method method;

    private Object controller;

    public HandlerMapping(String url, Method method, Object controller) {
        this.url = url;
        this.method = method;
        this.controller = controller;
    }

    //根据request获取对应的handler
    public final HandlerExecutionChain getHandler(HttpServletRequest request){
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
