package com.quizapp.QuizApp.controller;


import com.quizapp.QuizApp.entity.Question;
import com.quizapp.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/allquestions/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category){
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("/updatequestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/deletequestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }



}
