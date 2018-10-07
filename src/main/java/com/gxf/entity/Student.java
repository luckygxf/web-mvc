package com.gxf.entity;

import com.gxf.annotation.GxfColumn;
import com.gxf.annotation.GxfTable;


/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/7 下午4:45
 **/
@GxfTable(tableName = "student")
public class Student {

    @GxfColumn(column = "name", filed = "name")
    private String name;

    @GxfColumn(column = "address", filed = "address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
