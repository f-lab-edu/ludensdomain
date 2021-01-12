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

    @Before("@annotation(com.ludensdomain.aop.AdminCheck)")
    public void adminCheck() {
        AuthLevel authLevel = (AuthLevel) httpSession.getAttribute("ROLE_ADMIN");

        if (authLevel != AuthLevel.ADMIN) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Before("@annotation(com.ludensdomain.aop.CompanyCheck)")
    public void companyCheck() {
        AuthLevel authLevel = (AuthLevel) httpSession.getAttribute("ROLE_COMPANY");

        if (authLevel != AuthLevel.COMPANY) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Before("@annotation(com.ludensdomain.aop.UserCheck)")
    public void userCheck() {
        AuthLevel authLevel = (AuthLevel) httpSession.getAttribute("ROLE_USER");

        if (authLevel != AuthLevel.USER) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
