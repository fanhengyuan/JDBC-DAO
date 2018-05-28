package com.aiti.jdbc.test;

import com.aiti.jdbc.util.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 获取自动生成主键实现
 */
public class GenerateTest {

    @Test
    void test() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into student(name, age) values (?, ?);";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "哈哈");
            ps.setDouble(2, 26);
            ps.execute();

            //获得自动生成的键值
            rs = ps.getGeneratedKeys();
            if( rs.next() ) {
                int id = rs.getInt(1);
                System.out.println(id);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, null, rs);
        }
    }
}
