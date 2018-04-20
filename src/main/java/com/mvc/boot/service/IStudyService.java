package com.mvc.boot.service;

import com.mvc.boot.entity.Study;

public interface IStudyService {
    Study getStudyById(int id);
    void updateStudy(Study study);
}
