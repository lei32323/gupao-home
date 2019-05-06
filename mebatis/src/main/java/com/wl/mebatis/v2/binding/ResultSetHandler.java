package com.wl.mebatis.v2.binding;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class ResultSetHandler {

    public <T> void handle(ResultSet resultSet, T pojo) throws Exception {

        while (resultSet.next()) {
            for (Field declaredField : pojo.getClass().getDeclaredFields()) {
                try {
                    declaredField.setAccessible(true);
                    //获取名称
                    String name = declaredField.getName();

                    Object value = resultSet.getObject(name);
                    declaredField.set(pojo, value);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }
}
