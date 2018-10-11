package com.gxf.orm.utils;

import java.lang.reflect.Field;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 下午9:17
 **/
public class ColumnsUtil {

    /**
     * 获取column对应的property, 假设使用了驼峰式
     * */
    public static Field getColumnMappedProperty(String column, Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        column = column.replace("_", "");
        for(Field field : fields){
            if(field.getName().toUpperCase().equals(column.toUpperCase())){
                return field;
            }
        }
        return null;
    }
}
