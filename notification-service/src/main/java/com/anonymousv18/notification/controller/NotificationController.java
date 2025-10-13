package com.anonymousv18.notification.controller;

import com.anonymousv18.event.dto.NotificationEvent;
import com.anonymousv18.notification.dto.request.Receiver;
import com.anonymousv18.notification.dto.request.SendEmailRequest;
import com.anonymousv18.notification.service.IEmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {

    IEmailService emailService;

    @KafkaListener(topics = "notification-delivery")
    public void kafkaNotificationDelivery(NotificationEvent notificationEvent) {
        emailService.sendEmail(SendEmailRequest.builder()
                .to(Receiver.builder()
                        .email(notificationEvent.getReceiver())
                        .build())
                .subject(notificationEvent.getSubject())
                .htmlContent(notificationEvent.getBody())
                .build());
    }
    
}
