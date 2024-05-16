package com.quizapp.QuizApp.service;

import com.quizapp.QuizApp.Repository.QuestionRepository;
import com.quizapp.QuizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionRepository.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while adding question", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try{
            questionRepository.save(question);
            return new ResponseEntity<>("Question with the ID:"+question.getId()+" is updated successfully",HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while updating question", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            questionRepository.deleteById(id);
            return new ResponseEntity<>("Question with the ID:"+id+" is deleted successfully", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while deleting question", HttpStatus.NOT_FOUND);
    }
}
