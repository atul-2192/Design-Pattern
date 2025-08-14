package com.Notification.NotificationService.Service.Adapter;

import com.Notification.NotificationService.Model.CustomerDetails;
import com.Notification.NotificationService.Service.Adaptee.SMSService;
import com.Notification.NotificationService.Service.Target.NotificationSender;
import com.sun.nio.sctp.Notification;

public class SMSAdapter implements NotificationSender {
    private SMSService smsService;

    public SMSAdapter(SMSService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void sendNotification(CustomerDetails recipient, String message) {
        smsService.sendSMS(recipient, message);
    }
}
