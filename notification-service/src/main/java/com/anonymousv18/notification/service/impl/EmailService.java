package com.anonymousv18.notification.service.impl;

import com.anonymousv18.notification.dto.request.EmailRequest;
import com.anonymousv18.notification.dto.request.SendEmailRequest;
import com.anonymousv18.notification.dto.request.Sender;
import com.anonymousv18.notification.dto.response.EmailResponse;
import com.anonymousv18.notification.exception.AppException;
import com.anonymousv18.notification.exception.ErrorCode;
import com.anonymousv18.notification.repository.httpclient.IEmailClient;
import com.anonymousv18.notification.service.IEmailService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService implements IEmailService {

    IEmailClient emailClient;

    @Value("${notification.brevo.api-key}")
    @NonFinal
    String apiKey;

    @Override
    public EmailResponse sendEmail(SendEmailRequest sendEmailRequest) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Anonymous-V18")
                        .email("doquangving@gmail.com")
                        .build())
                .to(List.of(sendEmailRequest.getTo()))
                .htmlContent(sendEmailRequest.getHtmlContent())
                .subject(sendEmailRequest.getSubject())
                .build();

        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException _) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
