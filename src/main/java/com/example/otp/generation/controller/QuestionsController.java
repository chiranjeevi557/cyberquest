package com.example.otp.generation.controller;

import com.example.otp.generation.entity.Question;
import com.example.otp.generation.dto.QuestionModel;
import com.example.otp.generation.dto.UpdateQuestionModel;
import com.example.otp.generation.response.outputResponse;
import com.example.otp.generation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
public class QuestionsController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/{id}/execute")
    public Map<String, Object> executeCode(
            @PathVariable("id") Long questionId,
            @RequestBody Map<String, String> request) {
        String code = request.get("code");
        String language = request.get("language");
        String version = request.get("version");
        String email = request.get("email");

        return questionService.executeCode(questionId, code, language, version, email);
    }
    @PostMapping("/addquestion")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionModel questionModel){
        questionService.addQuestion(questionModel);

        return ResponseEntity.ok(new outputResponse(200,"Successfully Created"));
    }
    @GetMapping("/getquestion/{title}")
    public ResponseEntity<Question> getQuestion(@PathVariable String title) throws Exception {
        Question question = questionService.getQuestion(title);

        return ResponseEntity.ok(question);
    }

    @GetMapping("/gettitles")
    public ResponseEntity<List<String>> getQuestionTitles(){
        List<String> question = questionService.getAllTitles();

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
