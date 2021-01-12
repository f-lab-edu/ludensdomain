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
            switch (role) {
                case "1" :
                    httpSession.setAttribute(ROLE_ADMIN, AuthLevel.ADMIN);
                    break;
                case "2" :
                    httpSession.setAttribute(ROLE_COMPANY, AuthLevel.COMPANY);
                    break;
                case "3" :
                    httpSession.setAttribute(ROLE_USER, AuthLevel.USER);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void logout() {

        httpSession.invalidate();
    }
}
