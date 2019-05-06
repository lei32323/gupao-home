package com.mybaits.demo.handler;

import com.alibaba.fastjson.JSON;
import com.mybaits.demo.entity.Comment;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTypeHandler extends BaseTypeHandler<Comment> {


    //设置非空参数
    public void setNonNullParameter(PreparedStatement ps, int i, Comment parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("------------setNonNullParameter");
        String json = JSON.toJSONString(parameter);
        ps.setString(i, json);
    }

    //获取空结果集（根据列名），一般都是调用这个
    public Comment getNullableResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("------------getNullableResultByName");
        String json = rs.getString(columnName);
        Comment parse = JSON.parseObject(json,Comment.class);
        return (Comment) parse;
    }

    //获取空结果集（根据下标值）
    public Comment getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("------------getNullableResultByIndex");
        String json = rs.getString(columnIndex);
        return (Comment) JSON.toJSON(json);
    }

    //存储过程用的
    public Comment getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("------------getNullableResultByCallable");
        String json = cs.getString(columnIndex);
        return null;
    }
}
