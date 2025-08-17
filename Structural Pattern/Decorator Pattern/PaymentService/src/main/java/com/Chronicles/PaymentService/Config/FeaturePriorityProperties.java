package com.Chronicles.PaymentService.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.feature-priority")
@Data
public class FeaturePriorityProperties {
    private boolean enabled = false;
    private List<String> order;
}