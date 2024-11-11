package com.example.otp.generation.service;

import com.example.otp.generation.Exception.ResourceNotFoundException;
import com.example.otp.generation.entity.Question;
import com.example.otp.generation.model.QuestionModel;
import com.example.otp.generation.model.UpdateQuestionModel;
import com.example.otp.generation.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
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

    }
