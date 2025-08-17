package com.Chronicles.PaymentService.Processor.decorators;

import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Domain.dto.PaymentResponse;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RewardPointsDecorator extends PaymentDecorator {
    public RewardPointsDecorator(PaymentProcessor wrapped) { super(wrapped); }

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentResponse resp = super.processPayment(request);
        int points = (int) Math.floor(request.getAmount() / 100);
        resp.setRewardPoints(resp.getRewardPoints() + points);
        log.debug("[Rewards] +{} points (total {})", points, resp.getRewardPoints());
        return resp;
    }
}