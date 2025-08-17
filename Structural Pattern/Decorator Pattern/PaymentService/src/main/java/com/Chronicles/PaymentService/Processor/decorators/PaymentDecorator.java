package com.Chronicles.PaymentService.Processor.decorators;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PaymentDecorator implements PaymentProcessor {
    protected final PaymentProcessor wrapped;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        return wrapped.processPayment(request);
    }
}