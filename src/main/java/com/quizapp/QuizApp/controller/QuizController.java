package com.quizapp.QuizApp.controller;

import com.quizapp.QuizApp.dto.CandidateResponse;
import com.quizapp.QuizApp.dto.QuestionRequest;
import com.quizapp.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQuestion, @RequestParam String title){
        return quizService.createQuiz(category, numOfQuestion, title);
    }

    @GetMapping("/getquizquestions/{id}")
    public ResponseEntity<List<QuestionRequest>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    // taking and storing response of the user to DB and then calculating and returning the result by comparing it with right answers
    @PostMapping("/submitresponse/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<CandidateResponse> response){
        return quizService.calculateResult(id, response);
    }


}
