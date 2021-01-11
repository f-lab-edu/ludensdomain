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
                httpSession.setAttribute("ROLE", AuthLevel.ADMIN);
            }
            if ("2".equals(role)) {
                httpSession.setAttribute("ROLE", AuthLevel.COMPANY);
            }
            if ("3".equals(role)) {
                httpSession.setAttribute("ROLE", AuthLevel.USER);
            }
        }
    }

    @Override
    public void logout() {

        httpSession.invalidate();
    }
}
