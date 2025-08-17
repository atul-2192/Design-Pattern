package com.Chronicles.PaymentService.Processor.gateway;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service("stripe")
public class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        // TODO: integrate Stripe SDK here (charge creation)
        log.debug("[Stripe] Charging amount={} {}", request.getAmount(), request.getCurrency());
        return PaymentResponse.builder()
                .gateway("Stripe")
                .finalAmount(request.getAmount())
                .currency(request.getCurrency())
                .status("SUCCESS")
                .rewardPoints(0)
                .transactionId("st_" + UUID.randomUUID())
                .build();
    }
}