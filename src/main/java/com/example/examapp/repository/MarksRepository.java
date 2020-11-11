package com.example.examapp.repository;

import java.util.List;
import java.util.UUID;

import com.example.examapp.model.Marks;

import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository{
    Marks saveMarks(Marks marks);
    Marks getMarks(UUID candidateId, UUID examId);
    void deleteMarks(UUID candidateId,UUID examId);

    List<Marks> findAllByExamId(UUID examId);
}
