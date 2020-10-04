package com.example.examapp.controller;

import com.example.examapp.model.Examiner;
import com.example.examapp.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value= "/examiners")
public class ExaminerController {

    @Autowired
    private ExaminerService examinerService;

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
    public Examiner getbyId(@PathVariable UUID id) {
        System.out.println(id);
        return examinerService.getByExaminerId(id);
    }

}

