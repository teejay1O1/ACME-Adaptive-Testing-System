package com.example.examapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Question")
@Setter
@Getter
public class Question {
    @Id
    private UUID quesID;
    @JsonProperty("ifCorrect")
    private UUID ifCorrect;        //questionID of next question if answered correctly
    @JsonProperty("ifIncorrect")
    private UUID ifIncorrect;

    @JsonProperty("questionString")
    private String questionString;           //the actual question sentence
    @JsonProperty("options")
    private List<String> options;  //options of the form a: option1 b:option2 c:option3...
    @JsonProperty("correctOption")
    private int correctOption;

    public Question(){
        this.quesID=UUID.randomUUID();
        this.options=new ArrayList<String>();
    }

    public Question(String questionString, List<String> options, int correctOption) {
        this.questionString = questionString;
        this.options = options;
        this.correctOption = correctOption;
    }
}