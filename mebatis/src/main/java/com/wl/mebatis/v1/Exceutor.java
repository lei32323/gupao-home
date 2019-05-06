package com.wl.mebatis.v1;

import java.sql.*;

public class Exceutor {
    public <T> T query(String sql, Object obj) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //执行
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp-mybatis?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true", "root", "root");
            statement = connection.createStatement();
            sql = String.format(sql, obj);
            System.out.println("sql：" + sql);
            resultSet = statement.executeQuery(sql);
            Blog blog = new Blog();
            while (resultSet.next()) {
                blog.setBid(resultSet.getInt("bid"));
                blog.setAuthorId(resultSet.getInt("author_id"));
                blog.setName(resultSet.getString("name"));
            }
            return (T) blog;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
