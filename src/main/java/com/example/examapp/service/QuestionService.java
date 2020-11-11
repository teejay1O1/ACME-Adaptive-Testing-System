package com.example.examapp.service;

import com.example.examapp.model.Candidate;
import com.example.examapp.model.Exam;
import com.example.examapp.model.Question;
import com.example.examapp.model.Response;
import com.example.examapp.repository.CandidateRepository;
import com.example.examapp.repository.QuestionRepository;
import com.example.examapp.repository.ResponseRepository;
import com.example.examapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    private ResponseRepository responseRepository;
    private CandidateService candidateService;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(CandidateService candidateService, ResponseRepository responseRepository,QuestionRepository questionRepository){
        this.responseRepository=responseRepository;
        this.candidateService=candidateService;
        this.questionRepository=questionRepository;
    }
    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question getQuestionById(UUID quesId){
        Optional<Question> foundQuestion= questionRepository.findById(quesId);
        if(foundQuestion.isEmpty()){
            return null;
        }
        else{
            return foundQuestion.get();
        }
    }

//    public void addOption

    public Question getNextCorrectQuestion(UUID currentQuesId){
        Question currentQues= getQuestionById(currentQuesId);
        UUID nextQuesId= currentQues.getIfCorrect();
        Question nextQues=getQuestionById(nextQuesId);
        return nextQues;
    }

    public Question getNextIncorrectQuestion(UUID currentQuesId){
        Question currentQues= getQuestionById(currentQuesId);
        UUID nextQuesId= currentQues.getIfIncorrect();
        Question nextQues=getQuestionById(nextQuesId);
        return nextQues;
    }

    public Question getNextQuestion(UUID currentQuesId, String jwtToken){
        UUID candidateId= candidateService.getCandidateIdFromJwt(jwtToken);
        UUID examId=candidateService.getExamIdByCandidateId(candidateId);
        Response response=responseRepository.getResponse(examId,candidateId,currentQuesId);
        int answer= response.getAnswer();
        Question currentQues= getQuestionById(currentQuesId);
        if(currentQues.getCorrectOption() + 1 == answer){
            UUID nextQuesId= currentQues.getIfCorrect();
            Question nextQues=getQuestionById(nextQuesId);
            nextQues.setCorrectOption(-1);
            return nextQues;
        }
        else{
            UUID nextQuesId= currentQues.getIfIncorrect();
            Question nextQues=getQuestionById(nextQuesId);
            nextQues.setCorrectOption(-1);
            return nextQues;
        }
    }

    public Question createNextCorrectQuestion(UUID firstQuestionId,Question ifCorrectQuestion){
        Question firstQuestion= getQuestionById(firstQuestionId);
        Question ifCorrectQues =questionRepository.save(ifCorrectQuestion);
        firstQuestion.setIfCorrect(ifCorrectQues.getQuesID());
        questionRepository.save(firstQuestion);
        return ifCorrectQues;
    }

    public Question createNextIncorrectQuestion(UUID firstQuestionId,Question ifIncorrectQuestion){
        Question firstQuestion= getQuestionById(firstQuestionId);
        Question ifIncorrectQues = questionRepository.save(ifIncorrectQuestion);
        firstQuestion.setIfIncorrect(ifIncorrectQues.getQuesID());
        questionRepository.save(firstQuestion);
        return ifIncorrectQues;
    }

    public void updateNextCorrectQuestion(UUID firstQuestionId, UUID ifCorrectQuestionId){
        Question firstQuestion= getQuestionById(firstQuestionId);
        firstQuestion.setIfIncorrect(ifCorrectQuestionId);
        questionRepository.save(firstQuestion);

    }

    public void updateNextIncorrectQuestion(UUID firstQuestionId,UUID ifIncorrectQuestionId){
        Question firstQuestion= getQuestionById(firstQuestionId);
        firstQuestion.setIfIncorrect(ifIncorrectQuestionId);
        questionRepository.save(firstQuestion);
    }

//    public void updateQuestion(UUID quesId){}


}
