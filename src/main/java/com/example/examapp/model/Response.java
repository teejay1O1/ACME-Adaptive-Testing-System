package com.example.examapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection= "Response")
@Getter
@Setter
public class Response {
    @JsonProperty("examId")
    private UUID examId;
    @JsonProperty("candidateId")
    private UUID candidateId;
    @JsonProperty("questionId")
    private UUID questionId;
    @JsonProperty("answer")
    private int answer;

    public Response(){
    }
    public Response(UUID examId, UUID candidateId, UUID questionId, int givenAnswer) {
        this.examId = examId;
        this.candidateId = candidateId;
        this.questionId = questionId;
        this.answer = givenAnswer;
    }


}
