package com.example.examapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Marks")
@Setter
@Getter
public class Marks {
    private UUID candidateId;
    private UUID examId;
    private int score;

}
