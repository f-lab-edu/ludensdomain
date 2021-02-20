package com.ludensdomain.service;

import com.ludensdomain.aop.AuthLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    public static final String ID = "ID";
    public static final String ROLE = "ROLE";

    @Override
    public void insertIdAndRole(long id, AuthLevel role) {
        httpSession.setAttribute(ID, id);
        httpSession.setAttribute(ROLE, role);
    }

    @Override
    public boolean isLoginUser(long id) {
        String sessionId = httpSession.getId();
        String enteredId = String.valueOf(id);

        return sessionId.equals(enteredId);
    }

    @Override
    public void logout() {

        httpSession.invalidate();
    }
}
