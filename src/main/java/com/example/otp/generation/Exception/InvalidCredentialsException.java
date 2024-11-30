package com.example.otp.generation.Exception;

import software.amazon.awssdk.services.ses.endpoints.internal.Value;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message){

        super(message);
    }
}
