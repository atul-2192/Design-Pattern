package com.Notification.NotificationService.Service.Adaptee;

import com.Notification.NotificationService.Model.CustomerDetails;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SMSService {

    private final String accountSid;
    private final String authToken;
    private final String fromNumber;

    public SMSService(String accountSid, String authToken, String fromNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
        Twilio.init(accountSid, authToken);
    }

    public void sendSMS(CustomerDetails customerDetails, String message) {
        String rawNumber = customerDetails.getPhoneNumber();

        // Remove spaces, dashes, and parentheses
        String cleanedNumber = rawNumber.replaceAll("[^0-9]", "");

        // If it starts with '0', remove it
        if (cleanedNumber.startsWith("0")) {
            cleanedNumber = cleanedNumber.substring(1);
        }

        // Add country code if missing (assuming India +91)
        if (!cleanedNumber.startsWith("91")) {
            cleanedNumber = "91" + cleanedNumber;
        }

        // Format in E.164 format
        String formattedNumber = "+" + cleanedNumber;

        // Send via Twilio
        Message.creator(
                new PhoneNumber(formattedNumber),
                new PhoneNumber(fromNumber),
                message
        ).create();

        System.out.println("[SMS API] Sent to: " + formattedNumber);
    }

}
