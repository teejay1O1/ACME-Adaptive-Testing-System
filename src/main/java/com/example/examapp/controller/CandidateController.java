package com.example.examapp.controller;

import com.example.examapp.model.Candidate;
import com.example.examapp.model.Examiner;
import com.example.examapp.service.CandidateService;
import com.example.examapp.service.ExaminerService;
import com.example.examapp.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value= "/candidates")
public class CandidateController {

    private CandidateService candidateService;
    private JwtUtil jwtUtil;

    @Autowired
    CandidateController(CandidateService candidateService,JwtUtil jwtUtil) {
        this.candidateService= candidateService;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping(value = "/create")
    public Candidate createCandidate(@RequestBody Candidate candidate) {
//        Examiner examiner1= new Examiner("Tanuj");
//        return examiner;
//        System.out.println(examiner.getName());
        String encodedPassword= new BCryptPasswordEncoder().encode(candidate.getPassword());
        candidate.setPassword(encodedPassword);
        return candidateService.createCandidate(candidate);
    }

    @GetMapping(value = "/{id}")
    public Candidate getCandidateById(@PathVariable UUID id) {
//        System.out.println(id);
        return candidateService.getCandidateById(id);
    }

    @PostMapping(value= "/deleteExam")
    @ApiOperation(value = "This Method nullifies the exam attribute of candidate",
    notes = "this is to be called once the exam is ended by the candidate or the session expires")
    public void deleteExamFromCandidate(@RequestHeader(name = "Authorization") String jwtToken){
        UUID candidateId=candidateService.getCandidateIdFromJwt(jwtToken);
        Candidate candidate=candidateService.getCandidateById(candidateId);
        candidate.setExam(null);

    }
}
