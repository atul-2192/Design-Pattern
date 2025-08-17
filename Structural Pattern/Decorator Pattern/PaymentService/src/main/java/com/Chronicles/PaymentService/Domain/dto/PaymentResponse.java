package com.Chronicles.PaymentService.Domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String gateway;
    private double finalAmount;
    private String currency;
    private String status; // SUCCESS/FAILED
    private int rewardPoints;
    private String transactionId;
}