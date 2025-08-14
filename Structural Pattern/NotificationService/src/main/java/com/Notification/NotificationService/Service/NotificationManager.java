package com.Notification.NotificationService.Service;

import com.Notification.NotificationService.Model.CustomerDetails;
import com.Notification.NotificationService.Service.Target.NotificationSender;

public class NotificationManager {
    private NotificationSender emailSender;
    private NotificationSender smsSender;

    public NotificationManager(NotificationSender emailSender, NotificationSender smsSender) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    public void notify(CustomerDetails customerDetails, String message, String type) {
        if ("email".equalsIgnoreCase(type)) {
            emailSender.sendNotification(customerDetails, message);
        } else if ("sms".equalsIgnoreCase(type)) {
            smsSender.sendNotification(customerDetails, message);
        } else {
            throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
    }
}
