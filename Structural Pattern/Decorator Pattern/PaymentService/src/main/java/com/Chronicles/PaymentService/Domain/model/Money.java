package com.Chronicles.PaymentService.Domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Money {
    private double amount;
    private String currency; // ISO code
}