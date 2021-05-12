package com.ludensdomain.service;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    HttpSession httpSession;

    @Mock
    private LoginService sessionLoginService;

    @Mock
    private UserMapper userMapper;

    UserDto user;
    UserDto encryptedUser;

    @BeforeEach
    void setup() {
        sessionLoginService = new SessionLoginService(httpSession);
        lenient().when(httpSession.getAttribute("1")).thenReturn("1");
    }

    @BeforeEach
    public void generateUser() {
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
    }

    @BeforeEach
    public void generateEncryptedUser() {
        encryptedUser = UserDto
                .builder()
                .id(1)
                .name("홍길동")
                .password("detpyrcne")
                .email("user@mail.com")
                .dateOfBirth(new Date())
                .phoneNo("01011112222")
                .role("3")
                .build();
    }

    // getUserInfo (로그인 기능) 테스트
    @Test
    @DisplayName("아이디와 비밀번호가 일치하면 로그인에 성공한다.")
    public void logInSuccess() {
//        user = userMapper.getUserInfo(1);
//        UserDto checkUser = userService.getUserInfo(user.getId(), user.getPassword());

//        given(userMapper.getUserInfo(1)).willReturn(user);
        assertEquals(1, user.getId());
    }

    @Test
    @DisplayName("비밀번호의 암호화가 되지 않는다면 로그인에 실패한다.")
    public void logInFailByUnencryptedPassword() {

    }

    @Test
    @DisplayName("아이디가 존재하지 않는 아이디라면 로그인에 실패한다.")
    public void logInFailByNonExistingId() {

    }

    @Test
    @DisplayName("유저의 아이디와 패스워드가 일치하지 않으면 로그인에 실패한다.")
    public void logInFailByUnmatchedIdAndPassword() {

    }

    @Test
    @DisplayName("유저의 아이디가 일치한다면 해당 아이디의 유저 정보를 가져온다.")
    public void getUserInfoSuccess() {
        userMapper.getUserInfo(ID);
        verify(userMapper).getUserInfo(ID);
    }

    // insertUserInfo (신규 가입 기능) 테스트
    @Test
    @DisplayName("새로운 유저가 존재하지 않는 아이디로 입력하면 신규 가입에 성공한다.")
    public void signInSuccess() {

    }

    @Test
    @DisplayName("기존에 있는 아이디를 입력하면 신규 가입에 실패한다.")
    public void signInFailedByDuplicatedId() {

    }

    @Test
    @DisplayName("아이디나 패스워드가 없는 경우 신규 가입에 실패한다.")
    public void signInFailedByNullValue() {

    }

    // updateUserInfo (유저 정보 수정 기능) 테스트
    @Test
    @DisplayName("유저 정보 수정에 성공한다.")
    public void updateInfoSuccess() {
        userMapper.updateUserInfo(user);
        verify(userMapper).updateUserInfo(user);
    }

    @Test
    @DisplayName("아이디 중복 여부를 확인하고 없다면 false를 반환")
    public void duplicatedIdFalseByNonExistingId() {

    }

    @Test
    @DisplayName("아이디 중복 여부를 확인하고 없다면 false를 반환")
    public void duplicatedIdTrueByExistingId() {

    }

    // deleteUser (게임 삭제 기능) 테스트
    @Test
    @DisplayName("현재 로그인한 아이디와 삭제하려는 아이디가 같다면 유저 정보 삭제에 성공한다.")
    public void deleteUserSuccess() {

    }

    @Test
    @DisplayName("현재 로그인한 아이디와 삭제하려는 아이디가 다르다면 유저 정보 삭제에 실패한다.")
    public void deleteUserFailedByDifferentId() {

    }

}
