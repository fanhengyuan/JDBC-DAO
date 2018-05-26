package com.aiti.jdbc.dao;

import com.aiti.jdbc.domain.Student;

import java.util.List;

public interface IStudentDao {
    void save(Student stu);

    void update(int id, Student stu);

    void delete(int id);

    Student get(int id);

    List<Student> getAll();
}
