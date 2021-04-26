package com.ludensdomain.aop;

import com.ludensdomain.advice.exceptions.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

import static com.ludensdomain.service.SessionLoginService.ID;

@Aspect
@Component
@RequiredArgsConstructor
public class SessionCheckAspect {

    private final HttpSession httpSession;

    @Before("@annotation(SessionCheck) && @annotation(sessionCheck)")
    public void sessionCheck(SessionCheck sessionCheck) {
        String id = (String) httpSession.getAttribute(ID);
        if (id.isEmpty()) {
            throw new UnauthorizedUserException();
        }
    }
}
