package com.ludensdomain.service;

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
    public void login(long id, String role) {
        httpSession.setAttribute(ID, id);
        httpSession.setAttribute(ROLE, role);
    }

    @Override
    public void logout() {

        httpSession.invalidate();
    }
}
