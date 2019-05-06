package com.wl.mebatis.v2.binding;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterHandler {

    private PreparedStatement  preparedStatement;

    public ParameterHandler(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }


    public void setParameter(Object[] args) throws SQLException {
        //设置参数
        for (int i = 0; i < args.length; i++) {
            int index = i+1;
            if(args[i] instanceof Integer){
                preparedStatement.setInt(index, (Integer) args[i]);
            }else if(args[i] instanceof Double){
                preparedStatement.setDouble(index, (Double) args[i]);
            }else if(args[i] instanceof Boolean){
                preparedStatement.setBoolean(index, (Boolean) args[i]);
            }else if(args[i] instanceof Long){
                preparedStatement.setLong(index, (Long) args[i]);
            }else{
                preparedStatement.setString(index, (String) args[i]);
            }
        }
    }
}
