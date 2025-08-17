package com.Chronicles.PaymentService.Processor.providers;

import com.Chronicles.PaymentService.Processor.PaymentProcessor;

import java.util.function.Function;

public interface PaymentDecoratorProvider {
    String getName();
    int getPriority(); // lower = earlier (optional if sorting enabled)
    Function<PaymentProcessor, PaymentProcessor> getDecoratorFn();
}
