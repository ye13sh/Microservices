package com.ye13sh.feign;

import com.ye13sh.model.QuestionWrapper;
import com.ye13sh.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
//    @GetMapping("question/generate")
//    public ResponseEntity<List<Integer>> getQuestionsForQuiz
//            (@RequestParam String categoryName, @RequestParam Integer numQuestions );
//
//    @PostMapping("question/getQuestions")
//    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
//
//    @PostMapping("question/getScore")
//    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions);
    @PostMapping("/question/getQuestions")//getQuestionByID method is in PostMapping because Quiz-Service will have Question Id not Question So we will post it to Quiz-Service
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
