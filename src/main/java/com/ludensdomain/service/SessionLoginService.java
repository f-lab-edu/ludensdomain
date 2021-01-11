package com.ludensdomain.service;

import com.ludensdomain.domain.AuthLevel;
import com.ludensdomain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_COMPANY = "ROLE_COMPANY";
    private static final String ROLE_USER = "ROLE_USER";

    @Override
    public boolean isLoginUser(long id) {
        String sessionId = httpSession.getId();
        String enteredId = String.valueOf(id);

        return sessionId.equals(enteredId);
    }

    @Override
    public void authLevelByRole(UserDto user) {
        String role = user.getRole();

        if (role != null) {
            if ("1".equals(role)) {
                httpSession.setAttribute(ROLE_ADMIN, AuthLevel.ADMIN);
            }
            if ("2".equals(role)) {
                httpSession.setAttribute(ROLE_COMPANY, AuthLevel.COMPANY);
            }
            if ("3".equals(role)) {
                httpSession.setAttribute(ROLE_USER, AuthLevel.USER);
            }
        }
    }

    @Override
    public void logout() {

        httpSession.invalidate();
    }
}
