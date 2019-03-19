package com.gupaoedu.home.jdbctemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class JdbcTemplate {

    //数据源
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //查询的方法
    public <T> List<T>  queryList(String sql,RowMapper<T> rowMapper){
       try {
           //获取connection
           Connection connection =  getConnection();

           //获取PreparedStatement
           PreparedStatement preparedStatement =  getPreparedStatement(sql,connection);

           //获取结果集ResultSet
           ResultSet resultSet = executeQuery(preparedStatement);

           //封装结果集
           List<T> result = paresResultSet(resultSet,rowMapper);

           //关闭结果集
           closeResultSet(resultSet);

           //关闭语句集
           closePreparedStatement(preparedStatement);

           //关闭connection
           closeConnection(connection);

       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }

    protected void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    protected  void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    protected <T> List<T> paresResultSet(ResultSet resultSet, RowMapper<T> rowMapper) throws Exception {
        List<T> result = new ArrayList<>();
        int index = 1;
        while (resultSet.next()){
            result.add(rowMapper.mapRow(resultSet, index++));
        }
        return result;

    }

    protected ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {
//        for(int i=0;i<arg.length;i++){
//            preparedStatement.setObject(i+1,arg[i]);
//        }
        return preparedStatement.executeQuery();

    }

    protected PreparedStatement getPreparedStatement(String sql, Connection connection) throws SQLException {
       return connection.prepareStatement(sql);
    }

    protected  Connection getConnection() throws SQLException {
            return this.dataSource.getConnection();
    }

}
