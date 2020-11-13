package com.ludensdomain.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityConstants {

    public static final ResponseEntity<Void> RESPONSE_OK;
    public static final ResponseEntity<Void> RESPONSE_BAD_REQUEST;
    public static final ResponseEntity<Void> RESPONSE_USER_CREATED;

    static {
        RESPONSE_OK = ResponseEntity.status(HttpStatus.OK).build();
        RESPONSE_BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        RESPONSE_USER_CREATED = ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
