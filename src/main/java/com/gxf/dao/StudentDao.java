package com.gxf.dao;

import com.gxf.annotation.GxfSelect;
import com.gxf.entity.Student;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/7 下午4:48
 **/
public interface StudentDao {

    void save(Student student);

    @GxfSelect("select * from student where name = #{name}")
    Student getByName(String name);
}
