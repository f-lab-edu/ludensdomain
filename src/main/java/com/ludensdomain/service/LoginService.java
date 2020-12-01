package com.ludensdomain.service;

import javax.servlet.http.HttpSession;

public interface LoginService {

    boolean verifyUser(long id, HttpSession httpSession);

    void logout(HttpSession httpSession);
}
