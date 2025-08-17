package com.Chronicles.PaymentService.Processor.decorators;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Util.CurrencyConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrencyConversionDecorator extends PaymentDecorator {
    private final CurrencyConverter converter;

    public CurrencyConversionDecorator(PaymentProcessor wrapped, CurrencyConverter converter) {
        super(wrapped);
        this.converter = converter;
    }

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        double original = request.getAmount();
        String originalCurrency = request.getCurrency();
        double converted = converter.toBase(original, originalCurrency);
        request.setAmount(converted);
        request.setCurrency(converter.getBase());
        log.debug("[FX] {} {} -> {} {}", original, originalCurrency, converted, converter.getBase());
        return super.processPayment(request);
    }
}