package com.example.examapp.service;

import com.example.examapp.model.Question;
import com.example.examapp.model.Response;
import com.example.examapp.repository.QuestionRepository;
import com.example.examapp.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResponseService {

    private ResponseRepository responseRepository;
    private QuestionService questionService;

    @Autowired
    public ResponseService(QuestionService questionService, ResponseRepository responseRepository){
        this.questionService=questionService;
        this.responseRepository=responseRepository;
    }

    public Response createResponse( UUID examId, UUID candidateId,UUID quesId , int answer){
        return responseRepository.saveResponse(new Response(examId,candidateId,quesId,answer));
    }

    public Response getResponse(UUID examId, UUID candidateId,UUID quesId){
        return responseRepository.getResponse(examId,candidateId,quesId);
    }


//    public int getScore(UUID candidateId, UUID examId){
//        List<Response> responsesByCandidate = responseRepository.getResponsesByCandidate(candidateId,examId);
//        int score= 0;
//        for(Response response : responsesByCandidate){
//            int givenAnswer= response.getAnswer() ;
//            UUID quesId=response.getQuestionId();
//            Question question= questionService.getQuestionById(quesId);
//            int correctAnswer= question.getCorrectOption();
//            if (givenAnswer == correctAnswer ){
//                score+=1;
//            }
//
//        }
//        return score;
//    }

}
