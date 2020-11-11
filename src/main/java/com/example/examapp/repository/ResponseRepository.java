package com.example.examapp.repository;

import com.example.examapp.model.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ResponseRepository {
    public Response saveResponse(Response response);
    public Response getResponse(UUID examId, UUID candidateId, UUID quesId);
    public List<Response> getResponses(UUID examId,UUID candidateId );
}