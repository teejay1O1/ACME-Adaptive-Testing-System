package com.example.examapp.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

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
    private UUID firstQuestion;//assume every exam has atleast 1 question
    @JsonProperty("examDurationMins")
            private int examDurationMins;
//    @JsonProperty("students")
//    private List<UUID> students;

    Exam(){
        this.examID=UUID.randomUUID();
//        this.students = new ArrayList<UUID>();
    }

    Exam(String examName,int examDurationMins){
        this.examName=examName;
        this.examID= UUID.randomUUID();
        this.examDurationMins=examDurationMins;
    }

}

