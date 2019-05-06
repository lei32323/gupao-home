package com.wl.mebatis.v2.binding;


import com.wl.mebatis.v2.session.DefaultSqlSession;

import java.lang.reflect.Proxy;

public class MapperProxyFactory<T> {

    //mapper接口
    private Class<T> mapperInterface;

    //类型
    private Class object;

    public MapperProxyFactory(Class clazz, Class pojo) {
        this.mapperInterface = clazz;
        this.object = pojo;
    }

    /**
     * 获取代理对象
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T newInstance(DefaultSqlSession sqlSession) {

        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{this.mapperInterface},
                new MapperProxy(sqlSession,object));
    }
}
