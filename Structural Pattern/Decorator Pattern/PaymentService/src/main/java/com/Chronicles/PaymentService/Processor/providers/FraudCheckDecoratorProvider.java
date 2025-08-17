package com.Chronicles.PaymentService.Processor.providers;

import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Processor.decorators.FraudCheckDecorator;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FraudCheckDecoratorProvider implements PaymentDecoratorProvider {
    @Override public String getName() { return "fraudCheck"; }
    @Override public int getPriority() { return 10; }
    @Override public Function<PaymentProcessor, PaymentProcessor> getDecoratorFn() {
        return FraudCheckDecorator::new;
    }
}