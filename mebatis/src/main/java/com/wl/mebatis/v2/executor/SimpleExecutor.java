package com.wl.mebatis.v2.executor;

public class SimpleExecutor implements Executor {


    @Override
    public <T> T findOne(String sql, Object[] args, Class<T> clazz) {

        //创建StatementHandler
        StatementHandler statementHandler = new StatementHandler();

        return statementHandler.query(sql,args,clazz);
    }
}
