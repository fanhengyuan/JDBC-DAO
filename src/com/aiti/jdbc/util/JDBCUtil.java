package com.aiti.jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
    public static DataSource ds = null;
    // 静态代码块 只执行一次
    static {
        // 1.加载驱动
        try{
            Properties p = new Properties();
            FileInputStream in = new FileInputStream("resource/db.properties");
            p.load(in);
            // 使用 DBCP 连接池
//            ds = BasicDataSourceFactory.createDataSource(p);
            // 使用 druid 连接池
            ds = DruidDataSourceFactory.createDataSource(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try{
            // 2.连接 Mysql
//            return DriverManager.getConnection(JDBCUtil.url, JDBCUtil.user, JDBCUtil.password);
            // 连接池
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
