package com.mvc.boot.dao.product.master;


import com.mvc.boot.entity.Student;


public interface StudentMasterMapper {
    int deleteByPrimaryKey(int uid);

    int insert(Student record);

    int insertSelective(Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
