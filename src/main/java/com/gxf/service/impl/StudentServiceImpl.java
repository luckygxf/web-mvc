package com.gxf.service.impl;

import com.gxf.annotation.GxfService;
import com.gxf.service.StudentService;

/**
 * @Author: <guanxiangfei@meituan.com>
 * @Description:
 * @Date: Created in : 2018/10/7 下午4:52
 **/
@GxfService(beanName = "studentService")
public class StudentServiceImpl implements StudentService {

    public String sing(){
        System.out.println("this is sudent singing");
        return "this is sudent singing";
    }

    public String laugh(){
        System.out.println();
        return "this is student laugh";
    }
}
