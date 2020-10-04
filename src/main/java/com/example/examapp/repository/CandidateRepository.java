package com.example.examapp.repository;


import com.example.examapp.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, UUID> {
}
