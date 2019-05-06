package com.wl.mebatis.v2.session;


import com.wl.mebatis.v2.executor.Executor;

public class DefaultSqlSession {

    private Executor executor;

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = configuration.newExecutor();
    }

    //获取mapper信息
    public <T> T getMapper(Class<T> clazz) {

        return configuration.getMapper(clazz, this);
    }

    public <T> T findOne(String statementId, Object[] param, Class pojo) {

        //获取sql
        String sql = configuration.getMapperStatement(statementId);

        return (T) executor.findOne(sql, param, pojo);
    }

    public Configuration getConfiguration() {
        return configuration;
    }


}
