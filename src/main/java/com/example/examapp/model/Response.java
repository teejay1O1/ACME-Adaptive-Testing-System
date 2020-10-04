package com.example.examapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection= "Response")
@Getter
@Setter
public class Response {
    private UUID examId;
    private UUID candidateId;
    private UUID questionId;
    private int answer;

    public Response(UUID examId, UUID candidateId, UUID questionId, int givenAnswer) {
        this.examId = examId;
        this.candidateId = candidateId;
        this.questionId = questionId;
        this.answer = answer;
    }


}
