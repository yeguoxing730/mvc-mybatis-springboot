package com.mvc.boot.controller;


import com.mvc.boot.entity.Student;
import com.mvc.boot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/teststudent", method = RequestMethod.GET)
    public String teststudent(int uid) {
        System.out.println(studentService.selectByPrimaryKey(2));
        Student student = new Student();
        student.setUid(uid);
        List<Student> students = studentService.selectByCondition(student);
        if(students.size()>0){
            Student student1 = students.get(0);
            student1.setAge(44);
            studentService.updateByPrimaryKey(student1);
        }
        String jsonResult = com.alibaba.fastjson.JSON.toJSONString(students);
        return jsonResult;
    }
}
