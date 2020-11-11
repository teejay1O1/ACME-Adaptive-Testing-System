package com.example.examapp.controller;

import java.util.UUID;

import com.example.examapp.model.Examiner;
import com.example.examapp.service.ExaminerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/examiners")
public class ExaminerController {

    private ExaminerService examinerService;

    @Autowired
    ExaminerController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @PostMapping(value = "/create")
    public Examiner createExaminer(@RequestBody Examiner examiner) {
//        Examiner examiner1= new Examiner("Tanuj");
//        return examiner;
//        System.out.println(examiner.getName());
        return examinerService.createExaminer(examiner);
    }

    @GetMapping(value = "/{id}")
    public Examiner getbyId(@PathVariable UUID examinerId) {
//        System.out.println(id);
        return examinerService.getByExaminerId(examinerId);
    }

    @PutMapping(value = "addExam/{examinerId}")
    public Examiner addExamToExaminer(@PathVariable UUID examinerId,@RequestBody UUID examId){
        return examinerService.addExamToExaminer(examinerId,examId);
    }

}

