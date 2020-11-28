package com.ludensdomain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLogin implements LoginService {

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
