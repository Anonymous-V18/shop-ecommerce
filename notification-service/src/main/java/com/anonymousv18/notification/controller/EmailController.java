package com.anonymousv18.notification.controller;

import com.anonymousv18.notification.dto.request.SendEmailRequest;
import com.anonymousv18.notification.dto.response.ApiResponse;
import com.anonymousv18.notification.dto.response.EmailResponse;
import com.anonymousv18.notification.service.IEmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/email")
public class EmailController {

    IEmailService emailService;

    @PostMapping("/send")
    public ApiResponse<EmailResponse> send(@RequestBody SendEmailRequest request) {
        EmailResponse emailResponse = emailService.sendEmail(request);
        return ApiResponse.<EmailResponse>builder()
                .result(emailResponse)
                .build();

    }
}
