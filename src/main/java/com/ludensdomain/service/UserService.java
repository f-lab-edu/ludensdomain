package com.ludensdomain.service;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final StringEncryptor stringEncryptor;

    public Optional<UserDto> getUserInfo(long id, String password) {

        return userMapper.getUserInfo(id, password);
    }

    public void insertUserInfo(UserDto user) {
        UserDto encryptedUser = encryptUser(user);
        userMapper.insertUserInfo(encryptedUser);
    }

    public UserDto encryptUser(UserDto user){
        String encryptPassword = stringEncryptor.encrypt(user.getPassword());

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(encryptPassword)
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .phoneNo(user.getPhoneNo())
                .role(user.getRole())
                .build();
    }
}
