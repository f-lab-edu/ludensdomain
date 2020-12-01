package com.ludensdomain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    @Override
    public boolean verifyUser(long id, HttpSession httpSession) {
        String sessionId = httpSession.getId();
        String enteredId = String.valueOf(id);

        return sessionId.equals(enteredId);
    }

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
