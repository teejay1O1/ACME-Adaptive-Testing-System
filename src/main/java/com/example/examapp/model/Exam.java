package com.example.examapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection= "Exam")
@Getter
@Setter
public class Exam {
    @JsonProperty("examName")
    private String examName;
    @Id
    private UUID examID;
    @JsonProperty("examinerID")
    private UUID examinerID;
    @JsonProperty("firstQuestion")
    private UUID firstQuestion;     //assume every exam has atleast 1 question
    @JsonProperty("students")
    private List<UUID> students;

    Exam(){
        this.examID=UUID.randomUUID();
        this.students = new ArrayList<UUID>();
    }

    Exam(String examName){
        this.examName=examName;
        this.examID= UUID.randomUUID();
    }

}

