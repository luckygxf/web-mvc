package com.gxf.orm.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/11 下午4:21
 **/
public interface FieldHandler {

    /**
     * 通过反射设置字段value
     * */
    void setFiled(Object object, Field field, ResultSet resultSet, String columnName);
}
