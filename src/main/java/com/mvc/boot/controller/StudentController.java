package com.mvc.boot.controller;


import com.mvc.boot.entity.Student;
import com.mvc.boot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping()//http://127.0.0.1:8088/api/Student?uid=1
    public String Get(int uid) {
        System.out.println(studentService.selectByPrimaryKey(2));
        Student student = new Student();
        student.setUid(uid);
        List<Student> students = studentService.selectByCondition(student);
        String jsonResult = com.alibaba.fastjson.JSON.toJSONString(students);
        return jsonResult;
    }
}
