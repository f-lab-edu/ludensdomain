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
public class LoginCheckAspect {

    private final HttpSession httpSession;

    /**.
     * 로그인한 유저에 한해서 접근을 허용
     * @param loginCheck LoginCheck
     * @throws UnauthorizedUserException 허용하지 않는 접근
     */
    @Before("@annotation(LoginCheck) && @annotation(loginCheck)")
    public void loginCheck(LoginCheck loginCheck) {
        String id = (String) httpSession.getAttribute(ID);
        if (id.isEmpty()) {
            throw new UnauthorizedUserException();
        }
    }
}