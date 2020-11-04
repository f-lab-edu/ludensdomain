package com.ludensdomain.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class ResponseEntityStatus {

    public static final ResponseEntity<HttpStatus> SUCCESS = ResponseEntity.status(HttpStatus.OK).build();
    public static final ResponseEntity<HttpStatus> FAIL = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

}
