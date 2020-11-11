package com.example.examapp.service;

import java.util.Optional;
import java.util.UUID;

import com.example.examapp.model.Exam;
import com.example.examapp.repository.ExamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
