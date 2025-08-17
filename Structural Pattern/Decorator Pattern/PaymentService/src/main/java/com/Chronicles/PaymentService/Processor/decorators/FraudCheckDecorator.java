package com.Chronicles.PaymentService.Processor.decorators;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Exception.BadRequestException;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FraudCheckDecorator extends PaymentDecorator {
    public FraudCheckDecorator(PaymentProcessor wrapped) { super(wrapped); }

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        if (request.getAmount() > 100_000) { // demo threshold
            log.warn("[Fraud] High risk transaction detected: {} {}", request.getAmount(), request.getCurrency());
            throw new BadRequestException("High risk transaction detected");
        }
        log.debug("[Fraud] Risk check passed for {} {}", request.getAmount(), request.getCurrency());
        return super.processPayment(request);
    }
}