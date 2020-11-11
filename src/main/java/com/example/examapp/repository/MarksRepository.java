package com.example.examapp.repository;

import com.example.examapp.model.Marks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarksRepository{
    Marks saveMarks(Marks marks);
    Marks getMarks(UUID candidateId, UUID examId);
    void deleteMarks(UUID candidateId,UUID examId);

    List<Marks> findAllByExamId(UUID examId);
}
