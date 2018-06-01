package com.aiti.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

// 通用结果集处理器
public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> classType;
    public BeanHandler(Class<T> classType) {
        this.classType = classType;
    }
    @Override
    public T handle(ResultSet rs) throws Exception {
        if(rs.next()) {
            // 创建对象
            T obj = this.classType.newInstance();

            // 内省拿属性
            BeanInfo bf = Introspector.getBeanInfo(this.classType, Object.class);
            // 获取所有属性描述器
            PropertyDescriptor[] pds = bf.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                Object val = rs.getObject(pd.getName());
                // 给对象设置属性
                pd.getWriteMethod().invoke(obj, val);
            }
            return obj;
        }
        return null;
    }
}
