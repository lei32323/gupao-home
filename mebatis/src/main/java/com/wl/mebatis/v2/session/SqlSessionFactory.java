package com.wl.mebatis.v2.session;

/**
 * 创建sqlSession的工厂
 */
public class SqlSessionFactory {

    //需要一个configuration
    private Configuration configuration;

    //构建SqlSessionFactory
    public SqlSessionFactory build() {
        configuration = new Configuration();
        return this;
    }

    //打开一个sqlSession
    public DefaultSqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }

}
