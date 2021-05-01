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

import java.util.Date;

import static com.ludensdomain.aop.AuthLevel.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

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

//    @InjectMocks
//    private StringEncryptor stringEncryptor;

    /*
     * @Mock은 Mockito 라이브러리를 이용해 DTO 객체의 의존성을 제거해 반복적으로 테스트가 가능하게 만듦
     * userMapper의 경우 JDBC에 연동되는 객체로 당연히 bean이 생성이 안 되면 테스트는 무조건 실패한다.
     * 때문에 Mock(가짜 객체)을 선언해 데이터 연동 로직 수행 직전에 인터셉트해 대신 사용하도록 만든다.
     */
    @Mock
    private UserMapper userMapper;

    UserDto user;

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

    // 로그인 기능 테스트
    @Test
    @DisplayName("유저 로그인 통과")
    public void userLogInSuccess() {
        UserDto checkUser = userService.getUserInfo(user.getId(), user.getPassword());
        given(userMapper.getUserInfo(user.getId())).willReturn(user);

        assertEquals(user.getId(), checkUser.getId());
    }

    @Test
    @DisplayName("비밀번호 암호화 실패로 로그인 실패")
    public void userLogInFailByUnencryptedPassword() {

    }

    @Test
    @DisplayName("일치하는 아이디가 없어서 로그인 실패")
    public void userLogInFailByNonExistingId() {

    }

    @Test
    @DisplayName("아이디와 패스워드 불일치로 로그인 실패")
    public void userLogInFailByUnmatchedIdAndPassword() {

    }

    // 신규 가입 기능 테스트
    @Test
    @DisplayName("신규 가입 성공")
    public void userSignInSuccess() {

    }
}
