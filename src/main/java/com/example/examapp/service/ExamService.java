package com.example.examapp.service;

import com.example.examapp.model.Exam;
import com.example.examapp.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExamService {

    private ExamRepository examRepository;

    @Autowired
    ExamService(ExamRepository examRepository){
        this.examRepository=examRepository;
    }

    //create
    public Exam createExam(Exam exam){
        return examRepository.save(exam);
    }

    //retrieve
    public Optional<Exam> getExamById(UUID id){
        return examRepository.findById(id);
    }

    //update


    //delete
    public void deleteExamById(UUID id){
        examRepository.deleteById(id);
    }


}
