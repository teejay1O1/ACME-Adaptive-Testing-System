package com.example.examapp.service;

import com.example.examapp.model.Marks;
import com.example.examapp.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarksService {

    private final MarksRepository marksRepository;

    @Autowired
    public MarksService(MarksRepository marksRepository){
        this.marksRepository= marksRepository;
    }

    //create
    public Marks createMarks(Marks marks){
        return marksRepository.saveMarks(marks);
    }

    //retrieve
    public Marks getMarks(UUID candidateId, UUID examId){
        return marksRepository.getMarks(candidateId,examId);
    }

    //update
    public void updateMarks(UUID candidateId, UUID examId,int marksChange){
        Marks marks=getMarks(candidateId, examId);
        marks.setScore(marks.getScore() + marksChange);
        marksRepository.saveMarks(marks);
    }


    //delete
    public void deleteMarks(UUID candidateId, UUID examId){
        marksRepository.deleteMarks(candidateId,examId);
    }

    public List<Marks> getMarksForExam(UUID examId) {
        return marksRepository.findAllByExamId(examId);
    }
}
