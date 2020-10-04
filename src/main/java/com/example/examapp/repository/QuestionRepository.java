package com.example.examapp.repository;

import com.example.examapp.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends MongoRepository<Question, UUID> {

}
