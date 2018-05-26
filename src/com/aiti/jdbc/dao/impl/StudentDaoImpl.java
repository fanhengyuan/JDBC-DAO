package com.aiti.jdbc.dao.impl;

import com.aiti.jdbc.dao.IStudentDao;
import com.aiti.jdbc.domain.Student;
import com.aiti.jdbc.util.JDBCUtil;

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
        Statement st = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            Integer age = stu.getAge();
            String name = stu.getName();
            String sql = "insert into student(age, name) values ("+age+", '"+name+"');";
            System.out.println(sql);
            st = conn.createStatement();
            // 4.执行 sql
            int row = st.executeUpdate(sql);
            System.out.println(row);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, st, null);
        }
    }

    @Override
    public void update(int id, Student stu) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "update student set name='"+stu.getName()+"', age="+stu.getAge()+" where id ="+id+";";
            System.out.println(sql);
            st = conn.createStatement();
            // 4.执行 sql
            int row = st.executeUpdate(sql);
            System.out.println(row);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, st, null);
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
            int row = ps.executeUpdate();
            System.out.println(row);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    @Override
    public Student get(int id) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            // 3.sql 语句
            String sql = "select * from student where id ="+id+";";
            System.out.println(sql);
            st = conn.createStatement();
            // 4.执行 sql
            rs = st.executeQuery(sql);
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
            JDBCUtil.close(conn, st, rs);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();;

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
