package com.gxf.orm;

import com.gxf.annotation.GxfDelete;
import com.gxf.annotation.GxfInsert;
import com.gxf.annotation.GxfSelect;
import com.gxf.annotation.GxfUpdate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 上午11:41
 **/
public class GxfSqlCommand {
    private String name;
    private GxfSqlCommandType sqlCommandType;
    private Set<Class<? extends Annotation>> sqlAnnotationTypes = new HashSet<Class<? extends Annotation>>();


    /**
     * 解析注解，获取执行sql type(增、删、改、查)
     * */
    public GxfSqlCommand(Method method){
        sqlAnnotationTypes.add(GxfSelect.class);
        sqlAnnotationTypes.add(GxfDelete.class);
        sqlAnnotationTypes.add(GxfUpdate.class);
        sqlAnnotationTypes.add(GxfInsert.class);

        //解析注解拿到sql command type
        Class<? extends Annotation> sqlAnnotationType = null;
        for(Class<? extends Annotation> annotationType : sqlAnnotationTypes){
            Annotation type = method.getAnnotation(annotationType);
            if(type != null){
                sqlAnnotationType = annotationType;
                break;
            }
        } //for
        System.out.println("sqlAnnotationType.getSimpleName(): " + sqlAnnotationType.getSimpleName());
        sqlCommandType = GxfSqlCommandType.valueOf(sqlAnnotationType.getSimpleName().toUpperCase(Locale.ENGLISH));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GxfSqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(GxfSqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public Set<Class<? extends Annotation>> getSqlAnnotationTypes() {
        return sqlAnnotationTypes;
    }

    public void setSqlAnnotationTypes(Set<Class<? extends Annotation>> sqlAnnotationTypes) {
        this.sqlAnnotationTypes = sqlAnnotationTypes;
    }
}
