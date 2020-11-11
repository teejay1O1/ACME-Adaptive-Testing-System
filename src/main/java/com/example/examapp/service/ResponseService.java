package com.example.examapp.service;

import com.example.examapp.model.Question;
import com.example.examapp.model.Response;
import com.example.examapp.repository.QuestionRepository;
import com.example.examapp.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResponseService {

    private QuestionRepository questionRepository;
    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(QuestionRepository questionRepository ,ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
        this.questionRepository=questionRepository;
    }

    public Response createResponse(UUID examId, UUID candidateId, UUID quesId, int answer) {
        return responseRepository.saveResponse(new Response(examId, candidateId, quesId, answer));
    }

    public Response getResponse(UUID examId, UUID candidateId, UUID quesId) {
        return responseRepository.getResponse(examId, candidateId, quesId);
    }

    public int getAnswerForResponse(UUID examId, UUID candidateId, UUID quesId) {
        Response response = getResponse(examId, candidateId, quesId);
        return response.getAnswer();
    }

    public List<Response> getResponses(UUID examId, UUID candidateId) {
        return responseRepository.getResponses(examId, candidateId);
    }


    public int getScore(UUID candidateId, UUID examId) {
        List<Response> responsesByCandidate = responseRepository.getResponses(examId, candidateId);
        int score = 0;
        for (Response response : responsesByCandidate) {
            int givenAnswer = response.getAnswer();
            UUID quesId = response.getQuestionId();
            Optional<Question> foundQuestion = questionRepository.findById(quesId);
            Question question=foundQuestion.get();
            int correctAnswer = question.getCorrectOption();
            if (givenAnswer == correctAnswer) {
                score += 1;
            }

        }
        return score;
    }


}