package com.example.examapp.service;

import com.example.examapp.model.Candidate;
import com.example.examapp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CandidateService {
    private CandidateRepository candidateRepository;

    @Autowired
    CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository= candidateRepository;
    }

    public Candidate createCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
        return candidate;
    }
    public Candidate getCandidateById(UUID candidateId){
        Optional<Candidate> foundCandidate = candidateRepository.findById(candidateId);
        if(foundCandidate.isEmpty()){
            return null;
        }
        else{
            return foundCandidate.get();
        }
    }

    public void addExamToCandidate(UUID examId, UUID candidateId){
        Candidate candidate=getCandidateById(candidateId);
        List<UUID> exams=candidate.getExams();
        exams.add(examId);
        candidate.setExams(exams);
        candidateRepository.save(candidate);
    }

    public void deleteExamFromCandidate(UUID examId, UUID candidateId){
        Candidate candidate=getCandidateById(candidateId);
        List<UUID> exams=candidate.getExams();
        exams.remove(examId);
        candidate.setExams(exams);
        candidateRepository.save(candidate);
    }

    public void deleteCandidate(UUID candidateId){
        candidateRepository.deleteById(candidateId);
    }




}

