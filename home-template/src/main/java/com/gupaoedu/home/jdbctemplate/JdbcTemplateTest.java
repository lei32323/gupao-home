package com.gupaoedu.home.jdbctemplate;


import java.util.List;

public class JdbcTemplateTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao(null);
        List<UserEntity> userEntities = userDao.queryAll();
    }
}
