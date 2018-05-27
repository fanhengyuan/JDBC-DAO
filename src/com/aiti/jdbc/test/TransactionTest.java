package com.aiti.jdbc.test;

import com.aiti.jdbc.util.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * drop table if exists account;
 * CREATE TABLE `account` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(50) DEFAULT NULL,
 *   `money` double DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class TransactionTest  {
    @Test
    public void Test() {
        /**
         * zs 转1000块钱给 ls
         * 1.检查 zs 账户余额
         * 2.减少 zs 账户 1000
         * 3.增加 ls 账户 1000
         */
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from account where name = ? and money > ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, "zs");
            ps.setDouble(2, 1000);
            rs = ps.executeQuery();
            if( !rs.next() ) {
                throw new RuntimeException("zs没钱了");
            }

            // 2.减少  zs 账户
            sql = "update account set money = money - ? where name = ?;";
            System.out.println("事务开启--");
            conn.setAutoCommit(false); // 开启事物
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, 1000);
            ps.setString(2, "zs");
            ps.executeUpdate();

            int j = 1/0;

            // 3.增加 ls 账户
            sql = "update account set money = money + ? where name = ?;";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, 1000);
            ps.setString(2, "ls");
            ps.executeUpdate();
            conn.commit(); // 事务提交
            System.out.println("事务提交啦--");
        } catch (Exception e) {
            e.printStackTrace();

            try {
                System.out.println("事务回滚啦--");
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }finally {
            JDBCUtil.close(conn, ps, rs);
        }
    }
}
