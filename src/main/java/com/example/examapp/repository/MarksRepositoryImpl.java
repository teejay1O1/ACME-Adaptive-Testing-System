package com.example.examapp.repository;

import com.example.examapp.model.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;
import java.util.UUID;

@Repository
public class MarksRepositoryImpl implements MarksRepository{

    private final MongoTemplate mongoTemplate;

    @Autowired
    MarksRepositoryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }


    @Override
    public Marks saveMarks(Marks marks){
        return mongoTemplate.save(marks);
    }

    @Override
    public Marks getMarks(UUID candidateId, UUID examId) {
        Query getMarksQuery = new Query();
        getMarksQuery.addCriteria(Criteria.where("candidateId").is(candidateId).and("examId").is(examId));
        Marks marks=mongoTemplate.findOne(getMarksQuery,Marks.class);
        return marks;
    }


    @Override
    public List<Marks> findAllByExamId(UUID examId) {
        Query getMarksByExamQuery = new Query();
        getMarksByExamQuery.addCriteria(Criteria.where("examId").is(examId));
        return mongoTemplate.find(getMarksByExamQuery,Marks.class);

    }

    @Override
    public void deleteMarks(UUID candidateId, UUID examId) {
        Marks marks=getMarks(candidateId,examId);
        mongoTemplate.remove(marks);
    }
}
