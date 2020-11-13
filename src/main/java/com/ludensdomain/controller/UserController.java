package com.ludensdomain.controller;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import javax.validation.Valid;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_BAD_REQUEST;
import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;
import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_USER_CREATED;

@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 로그인 기능.
     *
     * @param id    사용자 아이디
     * @param password  사용자 비밀번호
     * @return {@literal ResponseEntity<Void>}
     */
    @SuppressWarnings("checkstyle:RequireEmptyLineBeforeBlockTagGroup")
    @GetMapping("login")
    public ResponseEntity<Void> login(@Valid long id, String password) {
        ResponseEntity<Void> result;
        Optional<UserDto> user = userService.getUserInfo(id, password);

        if (!user.isPresent()) {
            result = RESPONSE_BAD_REQUEST;
        } else {
            result = RESPONSE_OK;
        }
        return result;
    }

    /**
     * 사용자 회원가입.
     *
     * @param user  사용자 정보
     * @return {@literal ResponseEntity<String>}
    * */
    @PostMapping("signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto user) {
        userService.insertUserInfo(user);

        return RESPONSE_USER_CREATED;
    }
}