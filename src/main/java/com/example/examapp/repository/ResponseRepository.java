package com.example.examapp.repository;

import java.util.List;
import java.util.UUID;

import com.example.examapp.model.Response;

public interface ResponseRepository {
    public Response saveResponse(Response response);
    public Response getResponse(UUID examId, UUID candidateId, UUID quesId);
    public List<Response> getResponses(UUID examId,UUID candidateId );
}