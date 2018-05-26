package com.aiti.jdbc.test;

import com.aiti.jdbc.dao.IStudentDao;
import com.aiti.jdbc.dao.impl.StudentDaoImpl;
import com.aiti.jdbc.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentDaoTest {
    @Test
    public void save() {
        System.out.println("Test");
        Student stu = new Student();
        stu.setName("ls");
        stu.setAge(21);

        // 保存数据
        IStudentDao dao = new StudentDaoImpl();
        dao.save(stu);
    }

    @Test
    public void get() {
        IStudentDao dao = new StudentDaoImpl();
        Student stu = dao.get(4);
        System.out.println(stu);
    }

    @Test
    public void getAll() {
        IStudentDao dao = new StudentDaoImpl();
        List<Student> list = dao.getAll();
        System.out.println(list);
    }

    @Test
    public void update() {
        Student stu = new Student();
        stu.setName("鲁班");
        stu.setAge(7);

        IStudentDao dao = new StudentDaoImpl();
        dao.update(0, stu);
    }

    @Test
    public void delete() {
        IStudentDao dao = new StudentDaoImpl();
        dao.delete(3);
    }
}
