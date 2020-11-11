package com.example.examapp.controller;

import com.example.examapp.model.Marks;
import com.example.examapp.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("marks")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @GetMapping(value= "/{candidateId}/{examId}")
    public Marks getMarks(@PathVariable UUID candidateId, @PathVariable UUID examId ){
        return marksService.getMarks(candidateId,examId);
    }

    @GetMapping(value="/{examId}")
    public List<Marks> getMarksForExam(@PathVariable UUID examId){
        return marksService.getMarksForExam(examId);
    }


}
