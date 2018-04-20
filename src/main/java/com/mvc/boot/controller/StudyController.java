package com.mvc.boot.controller;


import com.mvc.boot.entity.Study;
import com.mvc.boot.service.IStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private IStudyService studyService;

    @RequestMapping(value = "/teststudy", method = RequestMethod.GET)
    public String get(int id) {
        System.out.println(studyService.getStudyById(1));
        Study study= studyService.getStudyById(1);
        if(study!=null){
            study.setStudyname("update name ...");
            studyService.updateStudy(study);
        }
        return "id";
    }
}
