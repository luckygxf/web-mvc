package com.gxf.orm;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 下午2:05
 **/
public class JDBCConfig {
    public static String driverClassName;
    public static String url;
    public static String userName;
    public static String password;

    static {
        String resource = "jdbc.properties";
        InputStream inputStream = JDBCConfig.class.getClassLoader().getResourceAsStream(resource);
        init(inputStream);
    }

    private static void init(InputStream inputStream){
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            System.out.println("properties:" + properties);
            driverClassName = properties.getProperty("jdbc.driverClassName");
            url = properties.getProperty("jdbc.url");
            userName = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
