package com.ludensdomain.service;

import com.ludensdomain.aop.AuthLevel;

public interface LoginService {

    void login(long id, String role);

    boolean isLoginUser(long id);

    void logout();

}
