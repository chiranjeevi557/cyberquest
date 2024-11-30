package com.example.otp.generation.Exception;

public class UserNotFoundException extends RuntimeException{

       public UserNotFoundException(String message) {
            super(message);
        }

}
