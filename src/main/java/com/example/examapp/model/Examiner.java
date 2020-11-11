package com.example.examapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection= "Examiner")
@Getter
@Setter
public class Examiner {
    @Id
    private UUID id;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("exams")
    private List<UUID> exams;

    public Examiner(){
        this.id=UUID.randomUUID();
        this.exams= new ArrayList<UUID>();
    }

    public Examiner(String userName) {
        this.exams= new ArrayList<UUID>();
        this.id = UUID.randomUUID();
        this.userName = userName;
    }

}
