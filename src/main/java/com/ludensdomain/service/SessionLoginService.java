package com.ludensdomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;

    @Autowired
    public SessionLoginService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public boolean verifyUser(long id) {
        String sessionId = httpSession.getId();
        String enteredId = String.valueOf(id);

        return sessionId.equals(enteredId);
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }
}
