package com.ludensdomain.service;

import com.ludensdomain.advice.exceptions.DuplicatedUserException;
import com.ludensdomain.advice.exceptions.NonExistingUserException;
import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LoginService loginService;
    private final UserMapper userMapper;
    private final StringEncryptor stringEncryptor;

    @Transactional
    public UserDto login(long id, String password) {
        UserDto existedUser = Optional.ofNullable(getUserInfo(id)).orElseThrow(NonExistingUserException::new);
        String encryptPw = stringEncryptor.encrypt(password);
        if (encryptPw.equals(existedUser.getPassword())) {
            loginService.login(id, existedUser.getRole());
            return existedUser;
        } else {
            return null;
        }
    }

    public UserDto getUserInfo(long id) {

        return userMapper.getUserInfo(id);
    }

    public void insertUserInfo(UserDto user) {
        if (checkIdExists(user.getId())) {
            throw new DuplicatedUserException();
        }
        userMapper.insertUserInfo(encryptUser(user));
    }

    /**
     * 비밀번호를 암호화한 사용자 정보를 build.
     *
     * @param user UserDto 인스턴스 변수
     * @return {@literal ResponseEntity<Void>}
     */
    public UserDto encryptUser(UserDto user) {
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

    /**
     * 사용자 정보 수정
     *
     * @param user UserDto 인스턴스 변수
     */
    public void updateUserInfo(UserDto user) {

        userMapper.updateUserInfo(user);
    }

    /**
     * 유저 아이디 존재 여부 확인
     *
     * @param id 유저 아이디
     * @return true/false
     */
    public Boolean checkIdExists(long id) {

        return userMapper.checkIdExists(id);
    }

    public void changePassword(long id, String newPw) {

        userMapper.changePassword(id, stringEncryptor.encrypt(newPw));
    }

    /**
     * 사용자 정보 삭제
     *
     * @param id   사용자 아이디
     */
    public void deleteUser(long id) {

        userMapper.deleteUser(id);
    }
}
