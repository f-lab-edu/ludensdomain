package com.ludensdomain.service;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public Optional<UserDto> getUserInfo(long id, String password) {
        return userMapper.getUserInfo();
    }

    public void insertUserInfo(UserDto userDto) {
        userMapper.insertUserInfo(userDto);
    }
}
