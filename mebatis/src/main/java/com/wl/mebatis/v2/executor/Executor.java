package com.wl.mebatis.v2.executor;

public interface Executor {

    //查询单条数据
    <T> T findOne(String sql,Object[] args,Class<T> clzz);

}
