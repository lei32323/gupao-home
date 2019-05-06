package com.wl.mebatis.v1;


import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class Configuration {

    private static ResourceBundle sqlMappings;

    static {
        sqlMappings = ResourceBundle.getBundle("sql");
    }

    //获取sql
    public String findSql(String statementId) {

        return sqlMappings.getString(statementId);
    }

    public <T> T getMapper(Class<T> clazz,SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(sqlSession));
    }
}
