package com.wl.mebatis.v2.plugin;

public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;


    Object plugin(Object object);

}
