package com.aiti.jdbc.dao.impl;

import com.aiti.jdbc.dao.IStudentDao;
import com.aiti.jdbc.domain.Student;
import com.aiti.jdbc.util.JDBCUtil;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public void save(Student stu) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "insert into student(name, age) values (?, ?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getAge());

            // 4.执行 sql
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(int id, Student stu) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "update student set name=?, age=? where id =?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getAge());
            ps.setInt(3, id);

            // 4.执行 sql
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "delete from student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // 4.执行 sql
            ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    @Override
    public Student get(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "select * from student where id =?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            // 4.执行 sql
            rs = ps.executeQuery();
            if(rs.next()) {
                Student stu = new Student();
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stu.setId(rs.getInt("id"));
                return stu;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "select * from student;";
            System.out.println(sql);
            st = conn.createStatement();
            // 4.执行 sql
            rs = st.executeQuery(sql);

            List<Student> listStu = new ArrayList<>();
            while(rs.next()) {
                Student stu = new Student();
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stu.setId(rs.getInt("id"));
                listStu.add(stu);
            }
            return listStu;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, st, rs);
        }
        return null;
    }
}
