package com.anonymousv18.notification.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED(9999, "Uncategorized error !", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(9998, "You don't have permission !", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(9997, "Unauthenticated error !", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(9999, "Key not found in annotation for validation!", HttpStatus.BAD_REQUEST),
    CANNOT_SEND_EMAIL(1001, "Can't send email !", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
