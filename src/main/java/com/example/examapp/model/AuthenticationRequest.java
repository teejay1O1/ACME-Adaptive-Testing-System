package com.example.examapp.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String candidateId;
    private String password;

    public AuthenticationRequest(){
    }

    public AuthenticationRequest(String candidateId, String password) {
        this.candidateId = candidateId;
        this.password = password;
    }
}
