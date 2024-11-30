package com.example.otp.generation.service;

import com.example.otp.generation.Exception.ResourceNotFoundException;
import com.example.otp.generation.Exception.UserNotFoundException;
import com.example.otp.generation.entity.Question;
import com.example.otp.generation.dto.QuestionModel;
import com.example.otp.generation.dto.UpdateQuestionModel;
import com.example.otp.generation.entity.UserEntity;
import com.example.otp.generation.repository.QuestionRepository;
import com.example.otp.generation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private PistonService pistonService;

    @Autowired
    private UserRepository userRepository;

    public void addQuestion(QuestionModel questionModel) {
        Question question = new Question();
        question.setTitle(questionModel.getTitle());
        question.setDescription(questionModel.getDescription());
        question.setDifficulty(questionModel.getDifficulty());
        question.setTestCase(questionModel.getTestCase());
        question.setExpectedOutput(questionModel.getExpectedOutput());
        questionRepository.save(question);
    }

    public String deleteQuestion(String questionName) throws Exception {
        if (!questionRepository.existsByTitle(questionName)) {
            throw new ResourceNotFoundException("Question not found with title: " + questionName);
        }
        // Delete the question by title
        questionRepository.deleteByTitle(questionName);
        return "successfully Deleted";
    }

    public Question getQuestion(String questionName) throws Exception {
        return questionRepository.findByTitle(questionName)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with title: " + questionName));
    }

    public List<String> getAllTitles(){
        return questionRepository.findAllTitles();
    }

    public Question updateQuestionByTitle(String title, UpdateQuestionModel updatedFields) throws Exception {
        // Find the existing question by title
        Question existingQuestion = questionRepository.findByTitle(title)
                .orElseThrow(() -> new Exception("Question not found with title: " + title));

        // Loop through fields in the DTO using reflection
        for (Field dtoField : UpdateQuestionModel.class.getDeclaredFields()) {
            dtoField.setAccessible(true);
            try {
                // Get the value of the field in the DTO
                Object updatedValue = dtoField.get(updatedFields);

                // Only update if the field value is non-null
                if (updatedValue != null) {
                    // Find the corresponding field in the Question entity
                    Field entityField = Question.class.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);

                    // Set the value in the entity field
                    entityField.set(existingQuestion, updatedValue);
                }} catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        // Save the updated question back to the database
        return questionRepository.save(existingQuestion);
    }

    public Question getQuestionByTitle(String title) throws ResourceNotFoundException {
        return questionRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with title: " + title));
    }

    public void deleteQuestionByTitle(String title) throws ResourceNotFoundException {
        Question question = questionRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with title: " + title));
        questionRepository.delete(question);
    }



    public Map<String, Object> executeCode(Long questionId, String code, String language, String version, String email) {
        // Fetch the question by ID
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // Execute the user code with the test case
        Map<String, Object> result = pistonService.executeCode(
                language,
                version,
                code,
                question.getTestCase(),
                question.getExpectedOutput()
        );

        if(result.get("passed").equals(true)){
           UserEntity userEntity =  userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("user with email " + email + " not found"));
           userEntity.setProblems_solved_count(userEntity.getProblems_solved_count() + 1);
           userRepository.save(userEntity);
        }

        return result;
    }

    }
