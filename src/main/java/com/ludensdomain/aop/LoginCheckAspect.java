package com.ludensdomain.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

import static com.ludensdomain.controller.UserController.ROLE;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final HttpSession httpSession;

    /**.
     * 기능 인가를 결정하기 위한 권한 확인
     */
    @Before("@annotation(LoginCheck) && @annotation(loginCheck)")
    public void loginCheck(LoginCheck loginCheck) {
        AuthLevel authLevel = loginCheck.authLevel();
        switch (authLevel) {
            case ADMIN:
                adminCheck();
                break;
            case COMPANY:
                companyCheck();
                break;
            case USER:
                userCheck();
                break;
            default:
                break;
        }
    }

    private void adminCheck() {
        AuthLevel role = (AuthLevel) httpSession.getAttribute(ROLE);

        if (!(role == AuthLevel.ADMIN)) {
            throw new IllegalArgumentException(role + " has no access right");
        }
    }

    private void companyCheck() {
        AuthLevel role = (AuthLevel) httpSession.getAttribute(ROLE);

        if (!(role == AuthLevel.COMPANY)) {
            throw new IllegalArgumentException(role + " has no access right");
        }
    }

    private void userCheck() {
        AuthLevel role = (AuthLevel) httpSession.getAttribute(ROLE);

        if (!(role == AuthLevel.USER)) {
            throw new IllegalArgumentException(role + " has no access right");
        }
    }
}
