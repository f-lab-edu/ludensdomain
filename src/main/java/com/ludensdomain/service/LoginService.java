package com.ludensdomain.service;

import com.ludensdomain.dto.UserDto;

public interface LoginService {

    boolean isLoginUser(long id);

    void authLevelByRole(UserDto user);

    void logout();
}
