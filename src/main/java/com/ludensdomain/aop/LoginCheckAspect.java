package com.ludensdomain.aop;

import com.ludensdomain.domain.AuthLevel;
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

    @Before("@annotation(com.ludensdomain.aop.UserCheck) && @annotation(UserCheck)")
    public void userCheck(AuthLevel authLevel) throws Exception {
        if (authLevel == AuthLevel.ADMIN) {
            adminCheck();
        }
        if (authLevel == AuthLevel.USER) {
            userCheck();
        }
        if (authLevel == AuthLevel.COMPANY) {
            companyCheck();
        }
    }

    private long getCurrentUser() {

        return Long.parseLong(httpSession.getId());
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
