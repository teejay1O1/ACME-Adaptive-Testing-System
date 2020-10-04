package com.example.examapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Document(collection="Candidaite")
@Setter
@Getter
public class Candidate {
    @JsonProperty("name")
    private String name;
    private UUID candidateId;
    private List<UUID> exams;


    public Candidate(){
        this.candidateId=UUID.randomUUID();
        this.exams= new ArrayList<UUID>();
    }

    public Candidate(String name){
        this.name=name;
        this.candidateId=UUID.randomUUID();
        this.exams= new ArrayList<UUID>();
    }

}
