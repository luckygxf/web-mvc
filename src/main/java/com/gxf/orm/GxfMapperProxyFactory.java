package com.gxf.orm;

import java.lang.reflect.Proxy;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 上午11:26
 **/
public class GxfMapperProxyFactory<T> {
    private Class<T> mapperInterface;

    public GxfMapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 返回 mapper proxy
     * */
    public T  newInstance(){
        GxfMapperProxy<T> gxfMapperProxy = new GxfMapperProxy<T>(mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, gxfMapperProxy);
    }
}
