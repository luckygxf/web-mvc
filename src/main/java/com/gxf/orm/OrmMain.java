package com.gxf.orm;

import com.gxf.dao.PersonDao;
import com.gxf.entity.Person;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 下午9:36
 **/
public class OrmMain {

    public static void main(String[] args) {
        GxfMapperProxyFactory<PersonDao> gxfMapperProxyFactory = new GxfMapperProxyFactory(PersonDao.class);
        PersonDao personDao = gxfMapperProxyFactory.newInstance();
        Person person = personDao.getPersonByName("guanxiangfei");
        System.out.println("person: " + person);

        person = personDao.getPersonByAddress("beijing_jiuxianqiao");
        System.out.println("person: " + person);
    }
}
