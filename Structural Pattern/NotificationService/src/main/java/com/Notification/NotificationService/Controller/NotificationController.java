package com.Notification.NotificationService.Controller;

import com.Notification.NotificationService.Model.CustomerDetails;
import com.Notification.NotificationService.Model.NotificationRequest;
import com.Notification.NotificationService.Service.NotificationManager;
import com.Notification.NotificationService.Service.Target.NotificationSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {
    private final NotificationManager notificationManager;

    public NotificationController(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @PostMapping()
    public String sendNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationManager.notify(notificationRequest.getCustomerDetails(), notificationRequest.getMessage(), notificationRequest.getType());
        return "Notification sent!";
    }
}
