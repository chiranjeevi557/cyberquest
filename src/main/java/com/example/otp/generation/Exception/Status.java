package com.example.otp.generation.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status extends Throwable {
    private String statusCode;
    private String message;
}