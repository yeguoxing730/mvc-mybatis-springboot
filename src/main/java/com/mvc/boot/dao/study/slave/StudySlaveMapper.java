package com.mvc.boot.dao.study.slave;


import com.mvc.boot.entity.Study;


public interface StudySlaveMapper {

    Study selectByPrimaryKey(int uid);

}
