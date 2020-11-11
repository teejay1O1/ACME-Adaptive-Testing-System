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

    @GetMapping(value= "/{quesId}")
    public Question getQuestionById(@PathVariable UUID quesId){
        return questionService.getQuestionById(quesId);
    }


    @GetMapping(value= "/getNext/{currentQuestionId}")
    public Question getNextQuestion(@PathVariable UUID currentQuestionId,@RequestHeader(name= "Authorization") String jwtToken){
        return questionService.getNextQuestion(currentQuestionId,jwtToken);
    }


    @PutMapping(value= "/updateNextIncorrect/{firstQuestionId}")
    public void updateNextIncorrectQuestion(@PathVariable UUID firstQuestionId,@RequestBody UUID ifIncorrectQuestionId){
        questionService.updateNextIncorrectQuestion(firstQuestionId,ifIncorrectQuestionId);
    }

    @PutMapping(value= "/updateNextCorrect/{firstQuestionId}")
    public void updateNextCorrectQuestion(@PathVariable UUID firstQuestionId,@RequestBody UUID ifCorrectQuestionId){
        questionService.updateNextCorrectQuestion(firstQuestionId,ifCorrectQuestionId);
    }

    @PutMapping(value= "/createNextCorrect/{firstQuestionId}")
    public Question createNextCorrectQuestion(@PathVariable UUID firstQuestionId,@RequestBody Question ifCorrectQuestion){
        return questionService.createNextCorrectQuestion(firstQuestionId,ifCorrectQuestion);
    }

    @PutMapping(value= "/createNextIncorrect/{firstQuestionId}")
    public Question createNextIncorrectQuestion(@PathVariable UUID firstQuestionId,@RequestBody Question ifIncorrectQuestion){
        return questionService.createNextIncorrectQuestion(firstQuestionId,ifIncorrectQuestion);
    }

}
