package com.gupaoedu.home.jdbctemplate;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.core.annotation.AnnotationUtils;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * dao
 */
public class UserDao extends JdbcTemplate {

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }


    public List<UserEntity> queryAll() {
        String sql = "select name,age from user";
        return super.queryList(sql, new RowMapper<UserEntity>() {
            @Override
            public UserEntity mapRow(ResultSet rs, int row) throws Exception {
                return enEntity(rs,UserEntity.class);
            }
        });

    }

    private <T> T enEntity(ResultSet rs, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            //获取column类型的注解
            Field[] declaredFields = t.getClass().getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                declaredFields[i].setAccessible(true);
                String columnName = declaredFields[i].getName();
                Column declaredAnnotation = declaredFields[i].getDeclaredAnnotation(Column.class);
                if(declaredAnnotation!=null){
                    columnName = declaredAnnotation.name();
                }
                declaredFields[i].set(t,rs.getObject(columnName));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
