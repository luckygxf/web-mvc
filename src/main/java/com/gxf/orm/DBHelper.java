package com.gxf.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 下午2:03
 **/
public class DBHelper {


    /**
     * 获取数据库链接
     * */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(JDBCConfig.driverClassName);
            conn = DriverManager.getConnection(JDBCConfig.url, JDBCConfig.userName, JDBCConfig.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
