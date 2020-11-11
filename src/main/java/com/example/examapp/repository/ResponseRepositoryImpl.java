package com.example.examapp.repository;

import com.example.examapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ResponseRepositoryImpl implements ResponseRepository{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ResponseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Response saveResponse(Response response) {
        return mongoTemplate.save(response);
    }

    @Override
    public Response getResponse(UUID examId, UUID candidateId, UUID quesId){
        Query uniqueResponseQuery = new Query();
        uniqueResponseQuery.addCriteria(Criteria.where("candidateId").is(candidateId).and("examId").is(examId).and("questionId").is((quesId)));
        return mongoTemplate.findOne(uniqueResponseQuery, Response.class);

    }

    @Override
    public List<Response> getResponses(UUID examId,UUID candidateId){
        Query allResponsesQuery = new Query();
        allResponsesQuery.addCriteria(Criteria.where("candidateId").is(candidateId).and("examId").is(examId));
        return mongoTemplate.find(allResponsesQuery,Response.class);
    }

}
