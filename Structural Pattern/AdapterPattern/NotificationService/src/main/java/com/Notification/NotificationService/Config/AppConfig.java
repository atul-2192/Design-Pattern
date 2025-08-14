package com.Notification.NotificationService.Config;

import com.Notification.NotificationService.Service.Adaptee.EmailService;
import com.Notification.NotificationService.Service.Adaptee.SMSService;
import com.Notification.NotificationService.Service.NotificationManager;
import com.Notification.NotificationService.Service.Target.NotificationSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

@Configuration
public class AppConfig {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.fromNumber}")
    private String fromNumber;


    @Bean
    public EmailService emailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        return new EmailService(mailSender, templateEngine);
    }

    @Bean
    public SMSService smsService() {
        return new SMSService(accountSid, authToken, fromNumber);
    }
    @Bean
    public NotificationSender emailAdapter(EmailService emailService) {
        return new com.Notification.NotificationService.Service.Adapter.EmailAdapter(emailService);
    }
    @Bean
    public NotificationSender smsAdapter(SMSService smsService) {
        return new com.Notification.NotificationService.Service.Adapter.SMSAdapter(smsService);
    }
    @Bean
    public NotificationManager notificationmanager(NotificationSender emailAdapter, NotificationSender smsAdapter) {
        return new NotificationManager(emailAdapter, smsAdapter);
    }
}
