package com.example.examapp.service;

import com.example.examapp.model.Exam;
import com.example.examapp.repository.ExamRepository;
import com.example.examapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExamService {

    private ExamRepository examRepository;
    private CandidateService candidateService;


    @Autowired
    ExamService(ExamRepository examRepository,CandidateService candidateService)
    {   this.candidateService=candidateService;
        this.examRepository=examRepository;
    }

    public Exam createExam(Exam exam){
        return examRepository.save(exam);
    }

    public Exam getExamById(UUID id){
        Optional<Exam> foundExam= examRepository.findById(id);
        if (foundExam.isEmpty()){
            return null;
        }
        else{
            return foundExam.get();
        }
    }


    public UUID getFirstQuestionId(String jwtToken){

        UUID candidateId= candidateService.getCandidateIdFromJwt(jwtToken);
        UUID examId=candidateService.getExamIdByCandidateId(candidateId);
        return getExamById(examId).getFirstQuestion();
    }



    public void deleteExamById(UUID id){
        examRepository.deleteById(id);
    }


    public Exam setExamFirstQuestion(UUID examId, UUID questionId) {
        Exam exam= getExamById(examId);
        exam.setFirstQuestion(questionId);
        return examRepository.save(exam);
    }
}
