package com.gxf.orm.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 下午8:51
 **/
public class ResultSetUtil {

    /**
     * 解析ResultSet中 所有例名称
     * */
    public static List<String> getColumnsFromResultSet(ResultSet resultSet){
        List<String> columns = new ArrayList<String>();
        ResultSetMetaData resultSetMetaData = null;
        try {
            resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                //这里不用别名
                columns.add(resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columns;
    }
}
