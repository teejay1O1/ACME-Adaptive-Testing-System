package com.example.examapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Document(collection="Candidate")
@Setter
@Getter
public class Candidate {
    @Id
    private UUID candidateId;
    @JsonProperty("password")
    public String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("exam")
    private UUID exam;


    public Candidate(){
        this.candidateId=UUID.randomUUID();
    }

    public Candidate(String name,String password,UUID exams){
        this.password=password;
        this.name=name;
        this.candidateId=UUID.randomUUID();
        this.exam=exam;
    }

}
