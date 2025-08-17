package com.Chronicles.PaymentService.Processor.providers;

import org.springframework.stereotype.Component;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Processor.decorators.RewardPointsDecorator;
import java.util.function.Function;

@Component
public class RewardPointsDecoratorProvider implements PaymentDecoratorProvider {
    @Override public String getName() { return "rewardPoints"; }
    @Override public int getPriority() { return 40; }
    @Override public Function<PaymentProcessor, PaymentProcessor> getDecoratorFn() {
        return RewardPointsDecorator::new;
    }
}