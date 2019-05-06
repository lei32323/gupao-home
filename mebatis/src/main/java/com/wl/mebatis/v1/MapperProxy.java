package com.wl.mebatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //调用查询方法
        //获取statmentId
        String statmentId = method.getDeclaringClass().getName() + "." + method.getName();

        return sqlSession.query(statmentId, args[0]);
    }

}
