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

    @Before("@annotation(com.ludensdomain.aop.LoginCheck)")
    public void userCheck(LoginCheck.AuthLevel target) throws Exception {
        switch (target) {
            case ADMIN: adminCheck();
                break;
            case COMPANY: companyCheck();
                break;
            case USER: userCheck();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target);
        }
    }

    private long getCurrentUser() {
        Object obj = (httpSession.getAttribute("id"));

        return (long) obj;
    }

    private void adminCheck() throws Exception {
        long id = getCurrentUser();
        LoginCheck.AuthLevel role = userService.findUserById(id).getRole();

        if (!(role == LoginCheck.AuthLevel.ADMIN)) {
            throw new Exception();
        }
    }

    private void companyCheck() throws Exception {
        long id = getCurrentUser();
        LoginCheck.AuthLevel role = userService.findUserById(id).getRole();

        if (!(role == LoginCheck.AuthLevel.COMPANY)) {
            throw new Exception();
        }
    }

    private void userCheck() throws Exception {
        long id = getCurrentUser();
        LoginCheck.AuthLevel role = userService.findUserById(id).getRole();

        if (!(role == LoginCheck.AuthLevel.USER)) {
            throw new Exception();
        }
    }
}
