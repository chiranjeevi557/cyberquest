package com.example.otp.generation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OtpGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpGenerationApplication.class, args);
	}

}
