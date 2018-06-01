package com.aiti.jdbc.dao.impl;

import com.aiti.jdbc.dao.IStudentDao;
import com.aiti.jdbc.domain.Student;
import com.aiti.jdbc.handler.BeanHandler;
import com.aiti.jdbc.handler.IResultSetHandler;
import com.aiti.jdbc.util.CRUDTemplate;
import java.sql.ResultSet;

import java.util.ArrayList;
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
        IResultSetHandler<List<Student>> rh = new StuResultSetHardImp();
/*        List<Student> list = CRUDTemplate.executeQuery(sql, rh, id);
        return list.size() == 1 ? list.get(0) : null;*/
        // 使用通用结果集处理器
        return CRUDTemplate.executeQuery(sql, new BeanHandler<Student>(Student.class), id);
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from student;";
        return CRUDTemplate.executeQuery(sql, new StuResultSetHardImp());
    }

    @Override
    public Integer getCount() {
        String sql = "select count(*) as count from student;";
        return CRUDTemplate.executeQuery(sql, new StuCountResultSetIml());
    }
}

/*
 * 结果集处理器实现
 */
class StuResultSetHardImp implements IResultSetHandler <List<Student>>{
    @Override
    public List handle(ResultSet rs) throws Exception {
        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student stu = new Student();
            stu.setName(rs.getString("name"));
            stu.setAge(rs.getInt("age"));
            stu.setId(rs.getInt("id"));
            list.add(stu);
        }
        return list;
    }
}

// 数量
class StuCountResultSetIml implements IResultSetHandler<Integer>{
    @Override
    public Integer handle(ResultSet rs) throws Exception {
        if(rs.next()) {
            return rs.getInt("count");
        }
        return 0;
    }
}
