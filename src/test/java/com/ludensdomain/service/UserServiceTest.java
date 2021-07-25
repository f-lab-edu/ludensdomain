package com.ludensdomain.service;

import com.ludensdomain.advice.exceptions.DuplicatedUserException;
import com.ludensdomain.advice.exceptions.NonExistingUserException;
import com.ludensdomain.advice.exceptions.PasswordNotMatchingException;
import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import com.ludensdomain.util.BCryptEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Service 클래스의 Unit Test
 * @SpringBootTest는 통합 테스트용으로 사용된다. 해당 어노테이션은 test 실행 시 Spring을 실행시켜서 모든 빈이 생성되기 때문에 굉장히 느려진다.
 * 대신 @InjectMocks, @Mock을 사용하면 IoC 컨테이너를 생성하지 않아 빠른 테스트 환경을 만들 수 있다.
 * 주의할 점은 Mock으로 unit test를 진행하면 실제 환경에서 실행이 잘 안 될 수도 있다.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final long ID = 1;

    @InjectMocks
    private UserService userService;

    @Mock
    private LoginService sessionLoginService;

    @Mock
    private UserMapper userMapper;

    UserDto user;
    UserDto encryptedUser;
    UserDto wrongUser;

    @BeforeEach
    void setup() {
        user = UserDto
                .builder()
                .id(1)
                .name("홍길동")
                .password("aaa")
                .email("user@mail.com")
                .dateOfBirth(new Date())
                .phoneNo("01011112222")
                .role("3")
                .build();

        encryptedUser = UserDto
                .builder()
                .name("홍길동")
                .password(BCryptEncryptor.encrypt("aaa"))
                .email("user@mail.com")
                .dateOfBirth(new Date())
                .phoneNo("01011112222")
                .role("3")
                .build();

        wrongUser = UserDto
                .builder()
                .id(1)
                .name("홍길동")
                .password(BCryptEncryptor.encrypt("bbb"))
                .email("user@mail.com")
                .dateOfBirth(new Date())
                .phoneNo("01011112222")
                .role("3")
                .build();
    }

    @Test
    @DisplayName("존재하는 유저의 아이디면서 비밀번호가 일치한다면 로그인에 성공한다.")
    public void logInSuccessByMatchingIdAndPassword() {
        when(userMapper.getUserInfo(ID)).thenReturn(user);
        assertEquals(userService.getUserInfo(ID), user);
        verify(userMapper).getUserInfo(ID);

        assertTrue(BCryptEncryptor.isMatch(user.getPassword(), encryptedUser.getPassword()));

        doNothing().when(sessionLoginService).login(ID, encryptedUser.getPassword());
        sessionLoginService.login(ID, encryptedUser.getPassword());
        verify(sessionLoginService).login(ID, encryptedUser.getPassword());
    }

    @Test
    @DisplayName("존재하지 않는 유저의 아이디라면 로그인에 실패하면서 NonExistingUserException을 발생시킨다.")
    public void logInFailByNonExistingId() {
        when(userMapper.getUserInfo(ID)).thenReturn(null);
        assertNull(userService.getUserInfo(ID));
        verify(userMapper).getUserInfo(ID);
        assertThrows(NonExistingUserException.class, () -> userService.login(ID, user.getPassword()));
    }

    @Test
    @DisplayName("패스워드가 일치하지 않으면 로그인에 실패하면서 PasswordNotMatchingException을 발생시킨다.")
    public void logInFailByUnmatchedPassword() {
        when(userMapper.getUserInfo(ID)).thenReturn(user);
        assertEquals(userService.getUserInfo(ID), user);
        verify(userMapper).getUserInfo(ID);

        assertFalse(BCryptEncryptor.isMatch(user.getPassword(), wrongUser.getPassword()));
        assertThrows(PasswordNotMatchingException.class, () -> userService.login(ID, wrongUser.getPassword()));
    }

    @Test
    @DisplayName("아이디를 입력해 아이디가 매칭되는 유저 정보를 가져온다.")
    public void getUserInfoSuccess() {
        userService.getUserInfo(ID);
        verify(userMapper).getUserInfo(ID);
    }

    @Test
    @DisplayName("새로운 유저가 존재하지 않는 아이디를 입력하면 신규 가입에 성공한다.")
    public void signInSuccess() {
        when(userMapper.checkIdExists(ID)).thenReturn(false);
        userService.insertUserInfo(user);
        assertFalse(userService.checkIdExists(ID));
        verify(userMapper).insertUserInfo(any(UserDto.class));
    }

    @Test
    @DisplayName("새로운 유저가 기존에 있는 아이디를 입력하면 신규 가입에 실패하면서 DuplicatedUserException을 발생시킨다.")
    public void signInFailByDuplicatedId() {
        when(userMapper.checkIdExists(ID)).thenReturn(true);
        assertThrows(DuplicatedUserException.class, () -> userService.insertUserInfo(user));
        verify(userMapper).checkIdExists(any(long.class));
    }

    @Test
    @DisplayName("유저의 정보를 입력하면 유저 정보 수정에 성공한다.")
    public void updateUserInfoSuccess() {
        doNothing().when(userMapper).updateUserInfo(user);
        userService.updateUserInfo(user);
        verify(userMapper).updateUserInfo(user);
    }

    @Test
    @DisplayName("아이디 중복 여부를 확인하고 중복되지 않는다면 false를 반환한다.")
    public void isDuplicatedIdReturnFalse() {
        when(userMapper.checkIdExists(ID)).thenReturn(false);
        assertFalse(userMapper.checkIdExists(ID));
        verify(userMapper).checkIdExists(ID);
    }

    @Test
    @DisplayName("아이디 중복 여부를 확인하고 중복된다면 true를 반환한다.")
    public void isDuplicatedIdReturnTrue() {
        when(userMapper.checkIdExists(ID)).thenReturn(true);
        assertTrue(userMapper.checkIdExists(ID));
        verify(userMapper).checkIdExists(ID);
    }

    @Test
    @DisplayName("아이디와 변경될 패스워드를 입력하면 패스워드를 암호화해 새로 저장한다.")
    public void changePasswordSuccess() {
        userService.changePassword(ID, "01010");
        verify(userMapper).changePassword(any(long.class), any(String.class));
    }

    @Test
    @DisplayName("유저 아이디를 입력하면 유저의 정보를 삭제한다.")
    public void deleteUserInfoSuccess() {
        userService.deleteUser(ID);
        verify(userMapper).deleteUser(ID);
    }
}
