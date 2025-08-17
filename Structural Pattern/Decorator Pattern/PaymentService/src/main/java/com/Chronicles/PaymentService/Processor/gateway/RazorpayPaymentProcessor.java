package com.Chronicles.PaymentService.Processor.gateway;
import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service("razorpay")
public class RazorpayPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        log.debug("[Razorpay] Charging amount={} {}", request.getAmount(), request.getCurrency());
        return PaymentResponse.builder()
                .gateway("Razorpay")
                .finalAmount(request.getAmount())
                .currency(request.getCurrency())
                .status("SUCCESS")
                .rewardPoints(0)
                .transactionId("rzp_" + UUID.randomUUID())
                .build();
    }
}
