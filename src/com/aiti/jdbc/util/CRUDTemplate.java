package com.aiti.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
