package com.anonymousv18.address.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED(9999, "Uncategorized error !", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(9998, "You don't have permission !", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(9997, "Unauthenticated error !", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(9999, "Key not found in annotation for validation!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1001, "User not found !", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be at least {min} characters !", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least {min} characters !", HttpStatus.BAD_REQUEST),
    USER_EXIST(1004, "User already exist !", HttpStatus.CONFLICT),
    PERMISSION_NOT_FOUND(1005, "Name of permission isn't exist !", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND(1006, "Address not found !", HttpStatus.BAD_REQUEST),
    ADDRESS_EXISTED(1007, "Address already exist !", HttpStatus.CONFLICT),
    PROVINCE_NOT_FOUND(1008, "Province not found !", HttpStatus.BAD_REQUEST),
    DISTRICT_NOT_FOUND(1009, "District not found !", HttpStatus.BAD_REQUEST),
    WARD_NOT_FOUND(1010, "Ward not found !", HttpStatus.BAD_REQUEST),
    PROVINCE_EXISTED(1011, "Province already exist !", HttpStatus.CONFLICT),
    DISTRICT_EXISTED(1012, "District already exist !", HttpStatus.CONFLICT),
    WARD_EXISTED(1013, "Ward already exist !", HttpStatus.CONFLICT),
    ADDRESS_OTHER_INVALID(1014, "Address other must be at least {min} characters !", HttpStatus.BAD_REQUEST);


    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
