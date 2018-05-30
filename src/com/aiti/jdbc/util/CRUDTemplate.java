package com.aiti.jdbc.util;

import com.aiti.jdbc.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Student> executeQuery(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 集合存储学生信息
        List<Student> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);

            for(int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Student stu = new Student();
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stu.setId(rs.getInt("id"));
                list.add(stu);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}