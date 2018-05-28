package com.aiti.jdbc.test;

import com.aiti.jdbc.util.JDBCUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.aiti.jdbc.util.JDBCUtil.getConnection;

public class DBCPTest {


    @Test
    public void test() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from student;";
        try {
           conn = JDBCUtil.getConnection();
           st = conn.createStatement();
           rs = st.executeQuery(sql);
           while (rs.next()) {
               System.out.println("姓名="+rs.getString("name")+" 年龄="+rs.getInt("age"));
           }
        }catch (Exception e) {
           e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, st, rs);
        }
    }
}
