package com.Notification.NotificationService.Service.Adapter;

import com.Notification.NotificationService.Model.CustomerDetails;
import com.Notification.NotificationService.Service.Adaptee.EmailService;
import com.Notification.NotificationService.Service.Target.NotificationSender;

public class EmailAdapter implements NotificationSender {
    private EmailService emailService;

    public EmailAdapter(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendNotification(CustomerDetails recipient, String message) {
        emailService.sendEmail(recipient, message);
    }
}
