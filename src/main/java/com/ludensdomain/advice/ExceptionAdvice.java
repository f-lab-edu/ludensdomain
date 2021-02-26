package com.ludensdomain.advice;

import com.ludensdomain.advice.exceptions.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ExceptionResponse unauthorizedUserException() {
        return getResult("해당 기능을 이용할 수 있는 권한이 없습니다.");
    }

    /*
     * 모든 예외 클래스 객체의 메세지를 담는 util method
     */
    public ExceptionResponse getResult(String message) {

        return ExceptionResponse.builder().message(message).build();
    }
}
