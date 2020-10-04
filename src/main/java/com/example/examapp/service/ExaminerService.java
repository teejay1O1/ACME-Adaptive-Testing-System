package com.example.examapp.service;

import com.example.examapp.model.Examiner;
import com.example.examapp.repository.ExaminerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
    // not required

    //delete
    public void deleteById(UUID id){
        examinerRepository.deleteById(id);
    }


}
