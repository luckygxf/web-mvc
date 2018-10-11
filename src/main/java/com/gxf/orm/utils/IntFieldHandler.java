package com.gxf.orm.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/11 下午4:04
 **/
public class IntFieldHandler implements FieldHandler{
    private static FieldHandler instance = new IntFieldHandler();

    public void setFiled(Object object, Field field, ResultSet resultSet, String columnName){
        field.setAccessible(true);
        try {
            field.set(object, resultSet.getInt(columnName));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static FieldHandler getInstance(){
        return instance;
    }
}
