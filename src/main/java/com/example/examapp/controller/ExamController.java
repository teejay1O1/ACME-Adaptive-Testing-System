package com.example.examapp.controller;

import com.example.examapp.model.Candidate;
import com.example.examapp.model.Exam;
import com.example.examapp.model.Question;
import com.example.examapp.repository.QuestionRepository;
import com.example.examapp.service.ExamService;
import com.example.examapp.service.ExaminerService;
import com.example.examapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
