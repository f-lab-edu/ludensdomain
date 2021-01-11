package com.ludensdomain.aop;

import com.ludensdomain.domain.AuthLevel;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final HttpSession httpSession;

    @Before("@annotation(com.ludendomain.annotation.LoginCheck)")
    public void loginCheck() {
        AuthLevel authLevel = (AuthLevel) httpSession.getAttribute("ROLE_COMPANY");

        if (authLevel != AuthLevel.COMPANY) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
