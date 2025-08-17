package com.Chronicles.PaymentService.Processor.providers;

import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Processor.decorators.CurrencyConversionDecorator;
import com.Chronicles.PaymentService.Util.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CurrencyConversionDecoratorProvider implements PaymentDecoratorProvider {
    private final CurrencyConverter converter;
    @Override public String getName() { return "currencyConversion"; }
    @Override public int getPriority() { return 20; }
    @Override public Function<PaymentProcessor, PaymentProcessor> getDecoratorFn() {
        return (base) -> new CurrencyConversionDecorator(base, converter);
    }
}
