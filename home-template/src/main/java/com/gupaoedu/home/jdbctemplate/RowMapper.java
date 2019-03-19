package com.gupaoedu.home.jdbctemplate;

import java.sql.ResultSet;

public interface RowMapper<T>  {

    T mapRow(ResultSet rs,int row) throws Exception;



}
