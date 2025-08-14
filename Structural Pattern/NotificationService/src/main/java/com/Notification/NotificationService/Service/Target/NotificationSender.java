package com.Notification.NotificationService.Service.Target;

import com.Notification.NotificationService.Model.CustomerDetails;

public interface NotificationSender {
    void sendNotification(CustomerDetails recipient, String message);
}