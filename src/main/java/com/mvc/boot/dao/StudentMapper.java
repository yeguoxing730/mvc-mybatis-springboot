package com.mvc.boot.dao;


import com.mvc.boot.entity.Student;

import java.util.List;


public interface StudentMapper {
    int deleteByPrimaryKey(int uid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(int uid);

    List<Student> selectByCondition(Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
