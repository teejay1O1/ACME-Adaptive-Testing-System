package com.example.examapp.repository;

import com.example.examapp.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamRepository extends MongoRepository<Exam, UUID> {

}
