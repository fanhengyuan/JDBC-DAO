package com.aiti.jdbc.util;

import com.aiti.jdbc.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUDTemplate {
    public static int executeUpdate(String sql, Object...params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            ps = conn.prepareStatement(sql);

            // 可变参数赋值
            for(int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }

            // 4.执行 sql
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;
    }

    public static <T>T executeQuery(String sql, IResultSetHandler<T> rh, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);

            for(int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            rs = ps.executeQuery();

            // 处理结果集
             return rh.handle(rs);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
