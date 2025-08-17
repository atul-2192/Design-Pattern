package com.Chronicles.PaymentService.Processor.factory;


import com.Chronicles.PaymentService.Config.FeaturePriorityProperties;
import com.Chronicles.PaymentService.Domain.dto.PaymentRequest;
import com.Chronicles.PaymentService.Exception.NotSupportedException;
import com.Chronicles.PaymentService.Processor.PaymentProcessor;
import com.Chronicles.PaymentService.Processor.providers.PaymentDecoratorProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessorFactory {

    private final Map<String, PaymentProcessor> processors; // beans keyed by @Service name
    private final List<PaymentDecoratorProvider> providerList;
    private final FeaturePriorityProperties priorityProps;

    public PaymentProcessor buildProcessor(PaymentRequest request) {
        PaymentProcessor base = processors.get(request.getGateway().toLowerCase());
        if (base == null) {
            throw new NotSupportedException("Unsupported gateway: " + request.getGateway());
        }

        Map<String, PaymentDecoratorProvider> providers = providerList.stream()
                .collect(Collectors.toMap(PaymentDecoratorProvider::getName, p -> p));

        List<String> features = new ArrayList<>(request.getFeatures());
        if (priorityProps.isEnabled() && priorityProps.getOrder() != null && !priorityProps.getOrder().isEmpty()) {
            features = features.stream()
                    .sorted(Comparator.comparingInt(f -> indexOf(priorityProps.getOrder(), f)))
                    .toList();
            log.debug("Features sorted by priority: {}", features);
        }

        for (String feature : features) {
            PaymentDecoratorProvider provider = providers.get(feature);
            if (provider == null) throw new NotSupportedException("Unsupported feature: " + feature);
            Function<PaymentProcessor, PaymentProcessor> fn = provider.getDecoratorFn();
            base = fn.apply(base);
            log.debug("Applied decorator: {}", feature);
        }
        return base;
    }

    private int indexOf(List<String> order, String feature) {
        int idx = order.indexOf(feature);
        return idx >= 0 ? idx : Integer.MAX_VALUE; // unknown features go last
    }
}