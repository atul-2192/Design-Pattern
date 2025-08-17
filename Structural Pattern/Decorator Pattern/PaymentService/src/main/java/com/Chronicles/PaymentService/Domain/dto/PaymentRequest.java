package com.Chronicles.PaymentService.Domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaymentRequest {
    @Positive(message = "amount must be positive")
    private double amount;

    @NotBlank
    private String currency; // e.g., USD, INR

    @NotBlank
    private String gateway; // stripe, paypal, razorpay

    private List<@NotBlank String> features = new ArrayList<>();

    // Optional metadata
    private String customerId;
    private String orderId;
}