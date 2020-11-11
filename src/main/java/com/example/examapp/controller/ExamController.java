package com.example.examapp.controller;

import java.util.UUID;

import com.example.examapp.model.Exam;
import com.example.examapp.model.Question;
import com.example.examapp.repository.QuestionRepository;
import com.example.examapp.service.ExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "exam")
public class ExamController {

    private QuestionRepository questionRepository;
    private ExamService examService;

    @Autowired
    ExamController(ExamService examService,QuestionRepository questionRepository){
        this.questionRepository=questionRepository;
        this.examService=examService;
    }

    @PostMapping(value= "/create")
    public Exam createExam(@RequestBody Exam exam){
//        examinerService.addExamToExaminer(exam.getExaminerID(),exam);
        return examService.createExam(exam);
    }


    @GetMapping(value="first")
    public Question getFirstQuesion(@RequestHeader(name="Authorization") String jwtToken){
        Question question= questionRepository.findById(examService.getFirstQuestionId(jwtToken)).get();
        question.setCorrectOption(-1);
        return question;
    }

    // value= candidates/{candidateId}/exams/{examId}/{questionId}
    @GetMapping(value= "/{id}")
    public Exam getExamById(@PathVariable UUID id){
        return examService.getExamById(id);
    }

    @PutMapping(value= "first/{examId}")
    public Exam setExamFirstQuestion(@PathVariable UUID examId,@RequestBody UUID questionId){
        return examService.setExamFirstQuestion(examId,questionId);
    }
}
