package com.example.examapp.service;

import java.util.Optional;
import java.util.UUID;

import com.example.examapp.model.Examiner;
import com.example.examapp.repository.ExaminerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminerService {


    private ExaminerRepository examinerRepository;

    @Autowired
    ExaminerService(ExaminerRepository examinerRepository){
        this.examinerRepository=examinerRepository;
    }

    //create
    public Examiner createExaminer(Examiner examiner){
        return examinerRepository.save(examiner);
    }

    //retrieve
    public Examiner getByExaminerId(UUID id){
        Optional<Examiner> foundExaminer= examinerRepository.findById(id);
        if(foundExaminer.isEmpty()){
            return null;
        }
        else{
            return foundExaminer.get();
        }
    }

    //update
    public Examiner addExamToExaminer(UUID examinerId, UUID examId){
        Examiner examiner=getByExaminerId(examinerId);
        examiner.getExams().add(examId);
        return examinerRepository.save(examiner);
    }

    //delete
    public void deleteById(UUID id){
        examinerRepository.deleteById(id);
    }


}
