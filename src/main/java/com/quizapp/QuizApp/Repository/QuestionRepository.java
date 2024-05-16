package com.quizapp.QuizApp.Repository;

import com.quizapp.QuizApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM questionset q WHERE q.category=:category ORDER BY RAND() LIMIT :numOfQuestion", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numOfQuestion);
    
}
