package com.wl.mebatis.v2.binding;

import com.wl.mebatis.v2.session.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegister {

    //存储mapper对应的mapper代理类
    private final Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<Class<?>, MapperProxyFactory>();


    /**
     * 新增mapper对应的mapper代理工厂
     * @param clazz
     * @param pojo
     * @param <T>
     */
    public <T> void addMapper(Class<T> clazz,Class pojo){
        knownMappers.put(clazz,new MapperProxyFactory(clazz,pojo));
    }

    /**
     * 获取mapper代理对象
     * @param clazz
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession){
        MapperProxyFactory mapperProxyFactory = knownMappers.get(clazz);
        return (T) mapperProxyFactory.newInstance(sqlSession);
    }

}
