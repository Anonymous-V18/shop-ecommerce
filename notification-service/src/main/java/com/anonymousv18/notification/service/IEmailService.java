package com.anonymousv18.notification.service;

import com.anonymousv18.notification.dto.request.SendEmailRequest;
import com.anonymousv18.notification.dto.response.EmailResponse;

public interface IEmailService {

    EmailResponse sendEmail(SendEmailRequest sendEmailRequest);
}
