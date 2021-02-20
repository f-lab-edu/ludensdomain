package com.ludensdomain.service;

import com.ludensdomain.aop.AuthLevel;

public interface LoginService {

    void insertRole(AuthLevel role);

    boolean isLoginUser(long id);

    void logout();

}
