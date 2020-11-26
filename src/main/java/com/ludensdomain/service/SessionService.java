package com.ludensdomain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionService implements Session {

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
