package com.Notification.NotificationService.Service.Adaptee;

import com.Notification.NotificationService.Model.CustomerDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.management.remote.JMXServerErrorException;

public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }


    public void sendEmail(CustomerDetails recipient, String message) {
        // build Thymeleaf context
        Context context = new Context();
        context.setVariable("name", recipient.getName());
        context.setVariable("message", message);


        // template file name (resources/templates/notification-template.html)
        String htmlContent = templateEngine.process("email-template", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            // Use UTF-8 and mixed-related for inline images if needed later
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
            helper.setTo(recipient.getEmail());
            helper.setText(htmlContent, true); // true = HTML
            helper.setSubject("Important Notification from Chronicles");
            mailSender.send(mimeMessage);
            log.info("[EMAIL API] Sent HTML email to {}", recipient.getEmail());
        } catch (MessagingException ex) {
            log.error("Failed to send email to " + recipient.getEmail(), ex);
            // choice: rethrow as runtime or swallow; here we rethrow wrapped
            throw new RuntimeException("Failed to send email to " + recipient.getEmail(), ex);
        }
    }
}
