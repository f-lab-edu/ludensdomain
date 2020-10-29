package com.ludensdomain.service;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) { this.userMapper = userMapper; }

    public UserDto getUserInfo(UserDto userDto) { return userMapper.getUserInfo(); }

    public void insertUserInfo(UserDto userDto) { userMapper.insertUserInfo(userDto); }
}
