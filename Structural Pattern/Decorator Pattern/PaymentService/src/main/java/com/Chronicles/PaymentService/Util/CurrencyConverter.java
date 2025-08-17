package com.Chronicles.PaymentService.Util;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app.currency")
public class CurrencyConverter {
    private String base;
    private Map<String, Double> rates; // FX rate to base

    public double toBase(double amount, String fromCurrency) {
        if (fromCurrency == null || fromCurrency.equalsIgnoreCase(base)) return amount;
        Double rate = rates.get(fromCurrency.toUpperCase());
        if (rate == null) throw new IllegalArgumentException("Unknown currency: " + fromCurrency);
        return amount * rate;
    }
}