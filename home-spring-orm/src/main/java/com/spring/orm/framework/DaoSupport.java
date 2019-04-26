package com.spring.orm.framework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wanglei
 * @Date: 2019/4/26 19:39
 * @Description:
 */
public class DaoSupport {

    public static <T> List<?> select(T t) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //开始连接
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orm_db", "root", "root");

            Map<String, String> cloumnNames = new HashMap<String, String>();
            //获取sql
            String sql = parseSql(t, cloumnNames);


            preparedStatement = conn.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            //获取结果
            List<?> lists = parseResult(t.getClass(), cloumnNames, resultSet);
            return lists;
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    private static <T> List<?> parseResult(Class<?> clazz, Map<String, String> cloumnNames, ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        List<T> result = new ArrayList<T>();
        while (resultSet.next()) {
            T o = (T) clazz.getClass().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);

                Field field = clazz.getDeclaredField(cloumnNames.get(columnName));
                field.setAccessible(true);
                field.set(o, resultSet.getObject(columnName));

            }

            result.add(o);
        }
        return result;
    }

    private static <T> String parseSql(T t, Map<String, String> cloumnNames) throws Exception {
//        Map<Field,String> cloumnNames = new HashMap<Field, String>();
        if (!t.getClass().isAnnotationPresent(Entity.class)) {
            //不存在的话 抛出异常
            throw new RuntimeException("该对象没有被Entity注解，不能进行ORM");
        }

        String tableName = "";
        Table table = t.getClass().getAnnotation(Table.class);
        if (table != null) {
            tableName = table.name();
        } else {
            tableName = t.getClass().getSimpleName();
        }
        StringBuffer sql = new StringBuffer("select * from " + tableName + " where 1=1");
        //追加参数
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(Column.class)) {
                continue;
            }

            declaredField.setAccessible(true);

            Column col = declaredField.getAnnotation(Column.class);
            String columName = "";

            if (col == null) {
                columName = declaredField.getName();
            } else {
                columName = col.name();
            }
            cloumnNames.put(columName, declaredField.getName());

            Object value = declaredField.get(t);

            //只处理String类型
            if (value == null) {
                continue;
            }

            sql.append(" and " + columName + " = " + value);

        }

        return sql.toString();

    }


}
