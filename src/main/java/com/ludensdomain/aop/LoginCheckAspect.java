package com.ludensdomain.aop;

import com.ludensdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final HttpSession httpSession;
    private final UserService userService;

    @Before("@annotation(LoginCheck) && @annotation(loginCheck)")
    public void loginCheck(LoginCheck loginCheck) throws Exception {
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
                throw new IllegalArgumentException("Unexpected value: " + authLevel);
        }
    }

    private long getCurrentUser() {
        Object obj = httpSession.getAttribute("id");

        return (long) obj;
    }

    private void adminCheck() throws Exception {
        long id = getCurrentUser();
        AuthLevel role = userService.findUserById(id).getRole();

        if (!(role == AuthLevel.ADMIN)) {
            throw new Exception();
        }
    }

    private void companyCheck() throws Exception {
        long id = getCurrentUser();
        AuthLevel role = userService.findUserById(id).getRole();

        if (!(role == AuthLevel.COMPANY)) {
            throw new Exception();
        }
    }

    private void userCheck() throws Exception {
        long id = getCurrentUser();
        AuthLevel role = userService.findUserById(id).getRole();

        if (!(role == AuthLevel.USER)) {
            throw new Exception();
        }
    }
}
