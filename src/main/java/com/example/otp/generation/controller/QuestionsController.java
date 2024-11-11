package com.example.otp.generation.controller;

import com.example.otp.generation.entity.Question;
import com.example.otp.generation.model.QuestionModel;
import com.example.otp.generation.model.UpdateQuestionModel;
import com.example.otp.generation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
public class QuestionsController {

    @Autowired
    QuestionService questionService;
    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionModel questionModel){
        questionService.addQuestion(questionModel);

        return null;
    }
    @GetMapping("/getquestion/{title}")
    public ResponseEntity<Question> getQuestion(@PathVariable String title) throws Exception {
        Question question = questionService.getQuestion(title);

        return ResponseEntity.ok(question);
    }
    @PostMapping("/updatequestion/{title}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String title, @RequestBody UpdateQuestionModel questionModel) throws Exception {
        Question question = questionService.updateQuestionByTitle(title, questionModel);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/deletequestion/{title}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String title) throws Exception {
        String quote = questionService.deleteQuestion(title);
       return ResponseEntity.ok(quote);
    }
}
