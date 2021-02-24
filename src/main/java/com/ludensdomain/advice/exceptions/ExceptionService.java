package com.ludensdomain.advice.exceptions;

import com.ludensdomain.advice.ExceptionResponse;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {
    /**
     * 호출된 예외의 에러 코드와 에러 메시지 전송.
     *
     * @param code 에러 코드
     * @param message 에러 메시지
     * @return response 에러 코드와 메시지를 담은 ExceptionResponse
     */
    public ExceptionResponse getResult(int code, String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(code);
        response.setMessage(message);

        return response;
    }
}
