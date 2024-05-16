package com.quizapp.QuizApp.service;

import com.quizapp.QuizApp.Repository.QuestionRepository;
import com.quizapp.QuizApp.Repository.QuizRepository;
import com.quizapp.QuizApp.dto.CandidateResponse;
import com.quizapp.QuizApp.dto.QuestionRequest;
import com.quizapp.QuizApp.entity.Question;
import com.quizapp.QuizApp.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numOfQuestion, String title) {

        List<Question> question = questionRepository.findRandomQuestionsByCategory(category, numOfQuestion);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionRequest>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);  // getting all quiz from quiz table by one id

        List<Question> questionsFromDB = quiz.get().getQuestions(); // collecting questions from DB in <Question> format

        List<QuestionRequest> questionsForUsers = new ArrayList<>();

        // converting format from <Question> to <QuestionRequest> using for loop
        for (Question q : questionsFromDB){
            QuestionRequest questionDto = new QuestionRequest(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUsers.add(questionDto);
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<CandidateResponse> response) {

        Quiz quiz = quizRepository.findById(id).get();

        List<Question> questionsFromQuiz = quiz.getQuestions();

        int right = 0;
        int count = 0;

        // calculating correct number of questions using loop
        for(CandidateResponse candidateResponse : response){
            if(candidateResponse.getResponse().equals(questionsFromQuiz.get(count).getRightAnswer())){
                right ++;
                count ++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
