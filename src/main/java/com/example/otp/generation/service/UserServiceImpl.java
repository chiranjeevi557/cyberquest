package com.example.otp.generation.service;

import com.example.otp.generation.Exception.InvalidCredentialsException;
import com.example.otp.generation.Exception.Status;
import com.example.otp.generation.Exception.UserAlreadyExistsException;
import com.example.otp.generation.Exception.UserNotFoundException;
import com.example.otp.generation.dto.AdminLoginDto;
import com.example.otp.generation.dto.SignupDto;
import com.example.otp.generation.entity.AdminEntity;
import com.example.otp.generation.entity.Question;
import com.example.otp.generation.entity.UserEntity;
import com.example.otp.generation.repository.QuestionRepository;
import com.example.otp.generation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;


    public String registerUser(SignupDto signupRequest) {
        // Check if username or email is already in use
            if (userRepository.existsByUsername(signupRequest.getUsername()) ||
                    userRepository.existsByEmail(signupRequest.getEmail())) {
                throw new UserAlreadyExistsException("Username or email already exists");
        }


        UserEntity user = new UserEntity();
            user.setEmail(signupRequest.getEmail());
            user.setFirst_name(signupRequest.getFirst_name());
            user.setLast_name(signupRequest.getLast_name());
            user.setPassword(signupRequest.getPassword());
            user.setUsername(signupRequest.getUsername());
            user.setDate_joined(LocalDateTime.now());
        userRepository.save(user);
        return "user registered successfully";
    }


    public UserEntity loadUserByUsername(String usernameOrEmail) {
        // Logic to load user for authentication (Spring Security)
        return null;
    }

    public String validateUser(String email, String password)  {

        // UserEntity user = userRepository.findUserByUsername(username);
        UserEntity user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user with email " + email + " not found"));

        if (user.getPassword().equals(password)) {
            return "User Found";
        } else {
            throw new InvalidCredentialsException("Invalid password for email " + email);
        }
    }

    public UserEntity get_Profile(String email) {
        // Find admin by email
        UserEntity admin = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user with email " + email + " not found"));

        // Validate password
        return admin;
    }

}