package com.ye13sh.service;

import com.ye13sh.dao.QuizDao;
import com.ye13sh.feign.QuizInterface;
import com.ye13sh.model.QuestionWrapper;
import com.ye13sh.model.Quiz;
import com.ye13sh.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;
//    @Autowired
//    RestTemplate rt;

    private String url="http://localhost:8080/question/generate";

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

//        List<Integer> q = this.rt.getForObject(url, List.class);


        List<Integer> question = quizInterface.getQuestionsForQuiz(category, numQ).getBody(); //using feign
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(question); // It creates different table in quiz database to store question-service data
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
          Quiz quiz = quizDao.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionIds();
          ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
          return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
