package com.aiti.jdbc.dao.impl;

import com.aiti.jdbc.dao.IStudentDao;
import com.aiti.jdbc.domain.Student;
import com.aiti.jdbc.util.CRUDTemplate;

import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public void save(Student stu) {
        String sql = "insert into student(name, age) values (?, ?);";
        CRUDTemplate.executeUpdate(sql, stu.getName(), stu.getAge());
    }

    @Override
    public void update(int id, Student stu) {
        String sql = "update student set name=?, age=? where id =?;";
        CRUDTemplate.executeUpdate(sql, stu.getName(), stu.getAge(), id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from student where id = ?";
        CRUDTemplate.executeUpdate(sql, id);
    }

    @Override
    public Student get(int id) {
        String sql = "select * from student where id =?;";
        List<Student>  list = CRUDTemplate.executeQuery(sql, id);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from student;";
        return CRUDTemplate.executeQuery(sql);
    }
}
