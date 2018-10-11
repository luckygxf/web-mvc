package com.gxf.orm.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/11 下午4:04
 **/
public class StringFieldHandler implements FieldHandler{
    private static FieldHandler instance = new StringFieldHandler();

    public void setFiled(Object object, Field field, ResultSet resultSet, String columnName){
        field.setAccessible(true);
        try {
            field.set(object, resultSet.getString(columnName));
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
