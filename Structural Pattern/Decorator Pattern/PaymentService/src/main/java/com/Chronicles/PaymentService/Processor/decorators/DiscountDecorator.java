package com.Chronicles.PaymentService.Processor.decorators;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscountDecorator extends PaymentDecorator {
    public DiscountDecorator(PaymentProcessor wrapped) { super(wrapped); }

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        double before = request.getAmount();
        double after = before * 0.9; // 10% discount
        request.setAmount(after);
        log.debug("[Discount] {} -> {}", before, after);
        return super.processPayment(request);
    }
}
