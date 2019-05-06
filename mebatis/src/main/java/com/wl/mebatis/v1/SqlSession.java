package com.wl.mebatis.v1;

public class SqlSession {

    private Exceutor exceutor;

    private Configuration configuration;

    public SqlSession(Exceutor exceutor, Configuration configuration) {
        this.exceutor = exceutor;
        this.configuration = configuration;
    }

    /**
     * 获取Mapper
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        //返回代理对象
        return configuration.getMapper(clazz,this);
    }

    public <T> T query(String statmentId, Object obj) {
        //获取
        String sql = configuration.findSql(statmentId);
        return exceutor.query(sql, obj);
    }

}
