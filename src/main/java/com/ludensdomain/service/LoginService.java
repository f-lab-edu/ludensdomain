package com.ludensdomain.service;

import javax.servlet.http.HttpSession;

public interface LoginService {

    void logout(HttpSession httpSession);
}
