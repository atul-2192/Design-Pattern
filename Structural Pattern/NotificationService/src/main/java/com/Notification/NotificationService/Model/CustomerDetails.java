package com.Notification.NotificationService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {
    private String name;
    private String email;
    private String phoneNumber;
}
