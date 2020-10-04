package com.example.examapp.controller;

import com.example.examapp.model.Exam;
import com.example.examapp.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class ExamController {

    @Autowired
    private ExamService examService;

    ExamController(ExamService examService){
        this.examService=examService;
    }

    @PostMapping(value= "/create")
    public Exam createExam(@RequestBody Exam exam){
        return examService.createExam(exam);
    }

    @GetMapping(value= "/{id}")
    public Optional<Exam> getExamById(@PathVariable UUID id){
        return examService.getExamById(id);
    }

}
