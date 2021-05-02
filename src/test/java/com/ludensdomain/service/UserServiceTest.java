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
import org.springframework.mock.web.MockHttpSession;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/*
 * Service 클래스의 Unit Test
 * @SpringBootTest는 통합 테스트용으로 사용된다. 해당 어노테이션은 test 실행 시 Spring을 실행시켜서 모든 빈이 생성되기 때문에 굉장히 느려진다.
 * 대신 @InjectMocks, @Mock을 사용하면 IoC 컨테이너를 생성하지 않아 빠른 테스트 환경을 만들 수 있다.
 * 주의할 점은 Mock으로 unit test를 진행하면 실제 환경에서 실행이 잘 안 될 수도 있다.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    MockHttpSession mockHttpSession;

    private LoginService sessionLoginService;

    @Mock
    private UserMapper userMapper;

    UserDto user;

    @BeforeEach
    void setup() {
        sessionLoginService = new SessionLoginService(mockHttpSession);
    }

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

    // getUserInfo (로그인 기능) 테스트
    @Test
    @DisplayName("유저 로그인 통과")
    public void logInSuccess() {
        UserDto checkUser = userService.getUserInfo(user.getId(), user.getPassword());

        given(userMapper.getUserInfo(user.getId())).willReturn(user);

        assertEquals(user.getId(), checkUser.getId());
    }

    @Test
    @DisplayName("비밀번호 암호화 실패로 로그인 실패")
    public void logInFailByUnencryptedPassword() {

    }

    @Test
    @DisplayName("일치하는 아이디가 없어서 로그인 실패")
    public void logInFailByNonExistingId() {

    }

    @Test
    @DisplayName("아이디와 패스워드 불일치로 로그인 실패")
    public void logInFailByUnmatchedIdAndPassword() {

    }

    // insertUserInfo (신규 가입 기능) 테스트
    @Test
    @DisplayName("신규 가입 성공")
    public void signInSuccess() {

    }

    @Test
    @DisplayName("중복된 아이디로 신규 가입 실패")
    public void signInFailedByDuplicatedId() {

    }

    @Test
    @DisplayName("입력된 아이디가 null인 경우 가입 실패")
    public void signInFailedByNullValue() {

    }

    // updateUserInfo (사용자 정보 수정 기능) 테스트
    @Test
    @DisplayName("사용자 정보 수정 성공")
    public void updateInfoSuccess() {

        user = UserDto
                .builder()
                .name("조남길")
                .email("update@gmail.com")
                .dateOfBirth(new Date())
                .phoneNo("01022222222")
                .build();
        when(mockHttpSession.getId().equals(String.valueOf(123))).thenReturn(true);
        when(sessionLoginService.isLoginUser(123)).thenReturn(true);
        doNothing().when(userMapper).updateUserInfo(any(Long.class), any(UserDto.class));

        sessionLoginService.isLoginUser(123);

        userService.updateUserInfo(user, 123);

        verify(userMapper).updateUserInfo(any(Long.class), any(UserDto.class));
    }

    @Test
    @DisplayName("id 불일치로 사용자 정보 수정 실패")
    public void updateInfoFailedByDifferentId() {

    }

    // deleteUser (게임 삭제 기능) 테스트
    @Test
    @DisplayName("게임 삭제 성공")
    public void deleteUserSuccess() {

    }

    @Test
    @DisplayName("id 불일치로 게임 삭제 실패")
    public void deleteUserFailedByDifferentId() {

    }

}
