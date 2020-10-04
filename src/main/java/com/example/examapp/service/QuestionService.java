package com.example.examapp.service;

import com.example.examapp.model.Question;
import com.example.examapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

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

    public Question getNextQuestion(UUID currentQuesId, int answer){
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
        firstQuestion.setIfCorrect(ifCorrectQuestion.getQuesID());
        questionRepository.save(firstQuestion);
        return questionRepository.save(ifCorrectQuestion);
    }

    public Question createNextIncorrectQuestion(UUID firstQuestionId,Question ifIncorrectQuestion){
        Question firstQuestion= getQuestionById(firstQuestionId);
        firstQuestion.setIfIncorrect(ifIncorrectQuestion.getQuesID());
        questionRepository.save(firstQuestion);
        return questionRepository.save(ifIncorrectQuestion);
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
