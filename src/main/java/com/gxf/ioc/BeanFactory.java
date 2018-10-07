package com.gxf.ioc;

import com.gxf.annotation.GxfAutoWired;
import com.gxf.annotation.GxfService;
import com.gxf.utils.ScanningFile;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: <guanxianseng@163.com>
 * @Description: bean 工厂
 * @Date: Created in : 2018/10/7 下午4:54
 **/
public class BeanFactory {

    //bean集合 Object 类实例
    private static Map<String, Object> beanName2Bean = new HashMap<String, Object>();

    /**
     * 根据beanname获取bean
     * */
    public static Object getBean(String beanName){
        return beanName2Bean.get(beanName);
    }

    /**
     * 暂时只收集service package下的bean
     * */
    private static void parseBean(){
        List<Class> beanList = ScanningFile.scanPackage("com.gxf.service.impl");
        for(Class clazz : beanList){
            boolean annotationedByGxfService = clazz.isAnnotationPresent(GxfService.class);
            //没有被GxfService注解
            if(!annotationedByGxfService)
                continue;
            try {
                //实例化
                Object instance = clazz.newInstance();
                GxfService gxfService = (GxfService) clazz.getAnnotation(GxfService.class);
                beanName2Bean.put(gxfService.beanName(), instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入beans到controller
     * */
    private static void injectionBeans(){
        List<Class> controllers = ScanningFile.scanPackage("com.gxf.controllers");
        //遍历所有controller
        for(Class controller : controllers){
            Field[] declaredFileds = controller.getDeclaredFields();
            //遍历每个字段
            for(Field field : declaredFileds){
                boolean isAnnotationedByGxfAutoWired = field.isAnnotationPresent(GxfAutoWired.class);
                //没有被注解标注
                if(!isAnnotationedByGxfAutoWired)
                    continue;
                GxfAutoWired gxfAutoWired = field.getAnnotation(GxfAutoWired.class);
                String beanName = gxfAutoWired.beanName();
                Object bean = beanName2Bean.get(beanName);
                field.setAccessible(true);
                try {
                    field.set(null, bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 启动ioc
     * */
    public static void startIoc(){
        //扫描bean，初始化
        parseBean();
        //注入bean到controller中
        injectionBeans();
    }
}
