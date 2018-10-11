package com.gxf.dao;

import com.gxf.annotation.GxfSelect;
import com.gxf.entity.Person;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/11 上午10:00
 **/
public interface PersonDao {

    @GxfSelect("select * from person where name = #{name}")
    Person getPersonByName(String name);


    @GxfSelect("select * from person where home_address = #{homeAddress}")
    Person getPersonByAddress(String homeAddress);
}
