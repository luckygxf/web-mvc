package com.gxf.orm;

import com.gxf.annotation.GxfSelect;
import com.gxf.orm.utils.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date: Created in : 2018/10/10 上午11:30
 **/
public class GxfMapperProxy<T> implements InvocationHandler {
    private Class<T> mapperInterface;
    private Map<String, FieldHandler> column2FieldHandler = new HashMap<String, FieldHandler>();

    public GxfMapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 代理对象执行的方法
     * */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //要执行的sql type
        GxfSqlCommand gxfSqlCommand = new GxfSqlCommand(method);
        Object result = null;
        switch (gxfSqlCommand.getSqlCommandType()){
            case GXFSELECT:
                return query(method, args);
        }

        return null;
    }

    /**
     *
     * */
    private <E> E query(Method method, Object[] parameters){
        E object = null;
        GxfSelect gxfSelect = method.getAnnotation(GxfSelect.class);
        //select * from student where name = #{name}
        String selectSql = gxfSelect.value()[0];
        selectSql = replaceSelectSql(selectSql);
        System.out.println("selectSql: " + selectSql);
        Connection connection = DBHelper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            //获取参数类型
            if(parameters[0] instanceof Integer){
                preparedStatement.setInt(1, (Integer) parameters[0]);
            }else if(parameters[0] instanceof String){
                preparedStatement.setString(1, parameters[0].toString());
            }
            //查询数据库
            preparedStatement.execute();
            Class clazz = method.getReturnType();
            //实例化对象
            object = (E) clazz.newInstance();
            ResultSet resultSet = preparedStatement.getResultSet();
            parseFieldFieldHander(resultSet);
            List<String> columns = ResultSetUtil.getColumnsFromResultSet(resultSet);
            resultSet.next();
            for(String column : columns){
                System.out.println("column: " + column);
                Field field = ColumnsUtil.getColumnMappedProperty(column, object);
                FieldHandler fieldHandler = column2FieldHandler.get(column);
                fieldHandler.setFiled(object, field, resultSet, column);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * 用 ? 替换占位符 #{id}
     * select * from student where name = #{name}
     * */
    private String replaceSelectSql(String selectSql){
        StringBuilder dealedSelectSql = new StringBuilder();
        int startIndex = selectSql.indexOf("#{");
        dealedSelectSql.append(selectSql.substring(0, startIndex - 1));
        dealedSelectSql.append("?");
        System.out.println("dealedSelectSql = " + dealedSelectSql);
        return dealedSelectSql.toString();
    }

    /**
     * Field反射 设置字段值
     * */
    private void parseFieldFieldHander(ResultSet resultSet){
        ResultSetMetaData resultSetMetaData = null;
        try {
            resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                String columnName = resultSetMetaData.getColumnName(i);
                int columnType = resultSetMetaData.getColumnType(i);
                switch (columnType){
                    case Types.INTEGER:
                        column2FieldHandler.put(columnName, IntFieldHandler.getInstance());
                        break;
                    case Types.VARCHAR:
                        column2FieldHandler.put(columnName, StringFieldHandler.getInstance());
                        break;
                    case Types.CHAR:
                        column2FieldHandler.put(columnName, StringFieldHandler.getInstance());
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
