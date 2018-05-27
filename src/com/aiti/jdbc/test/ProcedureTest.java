package com.aiti.jdbc.test;

import com.aiti.jdbc.domain.Student;
import com.aiti.jdbc.util.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.*;

/*
 * 无输出参数存储过程
CREATE DEFINER=`root`@`%` PROCEDURE `jdbc_db`.`getStu`(in n varchar(50))
BEGIN
    SELECT * FROM student where name = n;
END
 */
public class ProcedureTest {
    @Test
    void getStu() throws SQLException {
        // 1.连接数据库
        Connection conn = JDBCUtil.getConnection();
        // 2.调用存储过程
        CallableStatement ps = conn.prepareCall("{ call getStu(?) }");
        // 3.设置参数
        ps.setString(1, "鲁班22");
        // 4.执行存储过程
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Student stu = new Student();
            stu.setId(rs.getInt("id"));
            stu.setName(rs.getString("name"));
            stu.setAge(rs.getInt("age"));
            System.out.println(stu);
        }
        JDBCUtil.close(conn, ps, rs);
    }

    /**
     * 调用输出参数存储过程
     * USE jdbc_db;
     * CREATE PROCEDURE getName(in i int, out n VARCHAR(50))
     * BEGIN
     *     SELECT name INTO n FROM student WHERE id = i;
     * END
     */
    @Test
    void getName() throws SQLException {
        // 1.连接数据库
        Connection conn = JDBCUtil.getConnection();
        // 2.调用存储过程
        CallableStatement ps = conn.prepareCall("{ call getName(?, ?) }");
        // 3.设置参数
        ps.setInt(1, 1);
        ps.registerOutParameter(2, Types.VARCHAR);
        ps.execute();
        String name = ps.getString(2);
        System.out.println(name);
        JDBCUtil.close(conn, ps, null);
    }
}
