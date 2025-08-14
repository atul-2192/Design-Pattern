package com.Notification.NotificationService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private CustomerDetails customerDetails;
    private String message;
    private String type;
}
