package com.ludensdomain.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityConstants {

    public static final ResponseEntity<Void> RESPONSE_OK = ResponseEntity.status(HttpStatus.OK).build();
    public static final ResponseEntity<Void> RESPONSE_NO_CONTENT = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

}
