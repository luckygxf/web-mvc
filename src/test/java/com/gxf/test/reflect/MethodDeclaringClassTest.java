package com.gxf.test.reflect;

import com.gxf.dao.StudentDao;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 上午11:53
 **/
public class MethodDeclaringClassTest {


    @Test
    public void testIntegerType(){
        Object n = 5;
        if(n instanceof Integer){
            System.out.println("5 is object");
        }else{
            System.out.println("5 is not object");
        }
    }


    @Test
    public void testMethodDeclaringClass(){
        try {
            Class<StudentDao> studentDaoClass = StudentDao.class;
            Method method = studentDaoClass.getMethod("getByName", String.class);
            Class delcaringClazz = method.getDeclaringClass();
            System.out.println(delcaringClazz.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
