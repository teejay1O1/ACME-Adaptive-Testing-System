package com.example.examapp.controller;

import com.example.examapp.model.Question;
import com.example.examapp.service.QuestionService;
import com.example.examapp.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "Question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
//    @Autowired
//    private ResponseService responseService;


    @PostMapping(value= "create")
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @GetMapping(value= "{currentQuestionId}/{answer}")
    public Question getNextQuestion(@PathVariable UUID currentQuestionId,@PathVariable int answer){
        //responseService.createResponse(UUID studentId, UUID examId, UUID quesId , answer)
        return questionService.getNextQuestion(currentQuestionId,answer);
    }


}
