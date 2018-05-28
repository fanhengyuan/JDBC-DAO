package com.aiti.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
    public static String url = "jdbc:mysql://127.0.0.1:3306/jdbc_db?useSSL=false";
    public static String user = "root";
    public static String password = "123456";
    public static String driverName = "com.mysql.cj.jdbc.Driver";
    public static BasicDataSource ds = null;
    // 静态代码块 只执行一次
    static {
        // 1.加载驱动
        try{
            ds = new BasicDataSource();
            ds.setDriverClassName(driverName);
            ds.setUsername(user);
            ds.setPassword(password);
            ds.setUrl(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try{
            // 2.连接 Mysql
//            return DriverManager.getConnection(JDBCUtil.url, JDBCUtil.user, JDBCUtil.password);
            // DBCP 连接池
            return ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if( st != null ) {
            try {
                st.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if( conn != null ) {
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
