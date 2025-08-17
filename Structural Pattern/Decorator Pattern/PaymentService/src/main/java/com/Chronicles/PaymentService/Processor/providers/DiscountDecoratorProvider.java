package com.Chronicles.PaymentService.Processor.providers;

import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Processor.decorators.DiscountDecorator;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DiscountDecoratorProvider implements PaymentDecoratorProvider {
    @Override public String getName() { return "discount"; }
    @Override public int getPriority() { return 30; }
    @Override public Function<PaymentProcessor, PaymentProcessor> getDecoratorFn() {
        return DiscountDecorator::new;
    }
}