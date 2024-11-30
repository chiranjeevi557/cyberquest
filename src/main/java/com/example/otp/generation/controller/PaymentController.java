package com.example.otp.generation.controller;

import com.example.otp.generation.dto.PaymentDto;
import com.example.otp.generation.repository.UserRepository;
import com.example.otp.generation.response.outputResponse;
import com.example.otp.generation.service.PaymentServiceStripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    private PaymentServiceStripe paymentService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create-subscription-session")
    public ResponseEntity<?> createSubscriptionSession(@RequestBody PaymentDto paymentDto) {
        try {
         //   String successUrl = "https://stripe.com/";
         //   String cancelUrl = "http://localhost:3000/payment/fail";
            Session session = paymentService.createSubscriptionSession(paymentDto.getSuccessUrl(), paymentDto.getFailureUrl());
            int updated = userRepository.updatePremiumStatusByEmail(paymentDto.getEmail());
    /*        if (updated > 0) {
                System.out.println("User upgraded to premium.");
            } else {
                System.out.println("User not found or already premium.");
            } */
            return ResponseEntity.ok(new outputResponse(200,session.getUrl()));
        } catch (StripeException e) {
            return ResponseEntity.ok(new outputResponse(500,"Error creating Stripe session:"));
        }
    }
}
