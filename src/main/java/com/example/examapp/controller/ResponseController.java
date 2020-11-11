package com.example.examapp.controller;

import com.example.examapp.model.Response;
import com.example.examapp.service.CandidateService;
import com.example.examapp.service.ExamService;
import com.example.examapp.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/response")
public class ResponseController {


    private ResponseService responseService;
    private CandidateService candidateService;


    @Autowired
    public ResponseController(ResponseService responseService, CandidateService candidateService) {
        this.responseService = responseService;
        this.candidateService = candidateService;
    }



    @PostMapping("/create")
    public Response createResponse(@RequestBody UUID questionId, @RequestBody int answer,@RequestHeader(name= "Authorization") String jwt ){
        UUID candidateId=candidateService.getCandidateIdFromJwt(jwt);
        UUID examId=candidateService.getExamIdByCandidateId(candidateId);
        return responseService.createResponse(examId,candidateId,questionId,answer);
    }

    @GetMapping("/{examId}/{candidateId}/{questionId}")
    public Response getResponseById(@PathVariable UUID examId,@PathVariable UUID candidateId,@PathVariable UUID questionId){
        return responseService.getResponse(examId,candidateId,questionId);

    }

    @GetMapping("/{examId}/{candidateId}")
    public List<Response> getResponses(@PathVariable UUID examId, @PathVariable UUID candidateId) {
        return responseService.getResponses(examId,candidateId);
    }




    }
