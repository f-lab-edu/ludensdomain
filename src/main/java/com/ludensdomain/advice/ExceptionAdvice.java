package com.ludensdomain.advice;

import com.ludensdomain.advice.exceptions.*;
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

    @ExceptionHandler(InsertFailedException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    protected ExceptionResponse insertFailedException() {

        return getResult("게임 추가에 실패했습니다.");
    }

    @ExceptionHandler(UpdateFailedException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    protected ExceptionResponse updateFailedException(String message) {

        return getResult(message);
    }

    @ExceptionHandler(DuplicatedUserException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    protected ExceptionResponse duplicatedUserException() {

        return getResult("이미 존재하는 유저입니다.");
    }

    @ExceptionHandler(NonExistingUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ExceptionResponse nonExistingUserException() {

        return getResult("존재하지 않는 아이디입니다.");
    }

    /*
     * 모든 예외 클래스 객체의 메세지를 담는 util method
     */
    public ExceptionResponse getResult(String message) {

        return ExceptionResponse.builder().message(message).build();
    }
}
