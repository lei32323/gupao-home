package com.wl.mebatis.v2.executor;

import com.wl.mebatis.v2.binding.ParameterHandler;
import com.wl.mebatis.v2.binding.ResultSetHandler;
import com.wl.mebatis.v2.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatementHandler {

    public <T> T query(String sql, Object[] args, Class<T> pojo) {
        T t = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            t = pojo.newInstance();
            //执行sql
            connection = getConnection();

            System.out.println("sql :"+sql);

            preparedStatement = connection.prepareStatement(sql);

            //设置参数

            ParameterHandler parameterHandler = new ParameterHandler(preparedStatement);
            parameterHandler.setParameter(args);


            //执行
            resultSet = preparedStatement.executeQuery();

            //处理结果
            ResultSetHandler resultSetHandler = new ResultSetHandler();
            resultSetHandler.handle(resultSet, t);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }


    private Connection getConnection() throws Exception {
        //执行
        Class.forName(Configuration.configMappings.getString("jdbc.driver"));
        return DriverManager.getConnection(Configuration.configMappings.getString("jdbc.url"),
                Configuration.configMappings.getString("jdbc.username"),
                Configuration.configMappings.getString("jdbc.password"));

    }

}
