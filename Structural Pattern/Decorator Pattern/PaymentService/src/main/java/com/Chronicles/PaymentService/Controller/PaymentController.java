package com.Chronicles.PaymentService.Controller;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Processor.factory.PaymentProcessorFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentProcessorFactory factory;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResponse process(@Valid @RequestBody PaymentRequest request) {
        log.info("Incoming payment: gateway={}, currency={}, amount={}, features={}",
                request.getGateway(), request.getCurrency(), request.getAmount(), request.getFeatures());
        PaymentProcessor chain = factory.buildProcessor(request);
        PaymentResponse response = chain.processPayment(request);
        log.info("Payment processed: txGateway={}, finalAmount={}, status={}, rewardPoints={}",
                response.getGateway(), response.getFinalAmount(), response.getStatus(), response.getRewardPoints());
        return response;
    }
}