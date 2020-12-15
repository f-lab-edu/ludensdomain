package com.ludensdomain.service;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_BAD_REQUEST;
import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final StringEncryptor stringEncryptor;
    private final LoginService loginService;

    public Optional<UserDto> getUserInfo(long id, String password) {

        return userMapper.getUserInfo(id, password);
    }

    public void insertUserInfo(UserDto user) {
        UserDto encryptedUser = encryptUser(user);
        userMapper.insertUserInfo(encryptedUser);
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
     * 사용자 정보 수정 비즈니스 로직.
     * 아이디로 같은 유저인지 확인 후 맞으면 수정한 다음 OK를 반환하고 아니면 BAD_REQUEST 를 반환.
     *
     * @param user UserDto 인스턴스 변수
     * @param id   사용자 아이디
     * @return {@literal ResponseEntity<Void>}
     */
    public ResponseEntity<Void> updateUserInfo(UserDto user, long id) {
        if (!isSameUser(id)) {
            return RESPONSE_BAD_REQUEST;
        }

        userMapper.updateUserInfo(id, user);
        return RESPONSE_OK;
    }

    public boolean isSameUser(long id) {

        return loginService.verifyUser(id);
    }

    /**
     * 사용자 정보 삭제 비즈니스 로직.
     *
     * @param id   사용자 아이디
     * @param password   사용자 패스워드
     * @return {@literal ResponseEntity<Void>}
     */
    public ResponseEntity<Void> deleteUser(long id, String password) {
        if (isSameUser(id)) {
            userMapper.deleteUser(id, password);
            return RESPONSE_OK;
        } else {
            return RESPONSE_BAD_REQUEST;
        }
    }
}
