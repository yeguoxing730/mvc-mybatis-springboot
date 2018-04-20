package com.mvc.boot.service;

import com.mvc.boot.annotation.StudyMasterDataSource;
import com.mvc.boot.annotation.StudySlaveDataSource;
import com.mvc.boot.dao.study.master.StudyMasterMapper;
import com.mvc.boot.dao.study.slave.StudySlaveMapper;
import com.mvc.boot.entity.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studyService")
public class StudyService implements IStudyService {
    @Autowired
    StudyMasterMapper studyMasterMapper;
    @Autowired
    StudySlaveMapper studySlaveMapper;
    @StudySlaveDataSource
    @Override
    public Study getStudyById(int id) {
        return studySlaveMapper.selectByPrimaryKey(id);
    }
    @StudyMasterDataSource
    @Override
    public void updateStudy(Study study) {
        studyMasterMapper.updateByPrimaryKey(study);
    }
}
