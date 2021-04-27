package com.ludensdomain.aop;

import com.ludensdomain.advice.exceptions.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

import static com.ludensdomain.service.SessionLoginService.ROLE;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleCheckAspect {

    private final HttpSession httpSession;

    /**.
     * 기능 인가를 결정하기 위한 권한 확인
     * 기능에 따른 권한을 담은 authLevel과 유저가 지닌 권한을 담은 role을 비교하고, 불일치하면 예외를 발생
     *
     * @param roleCheck SessionCheck
     * @throws UnauthorizedUserException 허용하지 않는 접근
     */
    @Before("@annotation(com.ludensdomain.aop.RoleCheck) && @annotation(roleCheck)")
    public void roleCheck(RoleCheck roleCheck) {
        AuthLevel authLevel = roleCheck.authLevel();
        AuthLevel role = (AuthLevel) httpSession.getAttribute(ROLE);
        if (authLevel != role) {
            throw new UnauthorizedUserException();
        }
    }
}
