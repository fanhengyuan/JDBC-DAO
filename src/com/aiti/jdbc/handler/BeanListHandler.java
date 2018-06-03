package com.aiti.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private  Class<T> classType;
    public BeanListHandler(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public List handle(ResultSet rs) throws Exception {
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            // 创建对象
            T obj = this.classType.newInstance();
            // 通过内省拿属性
            BeanInfo bf = Introspector.getBeanInfo(this.classType, Object.class);
            // 获取所有属性描述器
            PropertyDescriptor[] pds = bf.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                Object val = rs.getObject(pd.getName());
                // 给对象设置属性
                pd.getWriteMethod().invoke(obj, val);
            }
            // 将对象放到集合中
            list.add(obj);
        }
        return list;
    }
}
