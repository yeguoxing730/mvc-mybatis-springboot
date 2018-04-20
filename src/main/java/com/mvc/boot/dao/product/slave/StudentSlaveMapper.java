package com.mvc.boot.dao.product.slave;


import com.mvc.boot.entity.Student;

import java.util.List;


public interface StudentSlaveMapper {

    Student selectByPrimaryKey(int id);

    List<Student> selectByCondition(Student record);

}
