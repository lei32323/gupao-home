package com.wl.mebatis.v2.binding;

import com.wl.mebatis.v2.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {

    private DefaultSqlSession sqlSession;

    private Class pojo;

    public MapperProxy(DefaultSqlSession sqlSession, Class pojo) {
        this.sqlSession = sqlSession;
        this.pojo = pojo;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法的所属类
        String clazzName = method.getDeclaringClass().getName();
        String statementId = clazzName + "." + method.getName();
        if (sqlSession.getConfiguration().getMapperStatement(statementId) != null) {
            //执行代理的方法
            return sqlSession.findOne(statementId, args, pojo);
        }
        //执行原始方法
        return method.invoke(proxy, args);


    }
}
