package com.ludensdomain.controller;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.LoginService;
import com.ludensdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final LoginService loginService;

    /**
     * 사용자 로그인 기능.
     *
     * @param id        사용자 아이디
     * @param password  사용자 비밀번호
     * @return {@literal ResponseEntity<Void>}
     */
    @GetMapping("login")
    public ResponseEntity<Void> login(@Valid long id, String password) {
        ResponseEntity<Void> result;
        UserDto user = userService.getUserInfo(id, password);

        if (user == null) {
            result = RESPONSE_BAD_REQUEST;
        } else {
            loginService.authLevelByRole(user);
            result = RESPONSE_OK;
        }

        return result;
    }

    /**
     * 사용자 회원가입.
     *
     * @param user  사용자 정보
     * @return {@literal ResponseEntity<Void>}
     */
    @PostMapping("signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto user) {
        userService.insertUserInfo(user);

        return RESPONSE_USER_CREATED;
    }

    /**
     * 사용자 수정.
     *
     * @param user  사용자 정보
     * @param id    사용자 아이디
     */
    @PutMapping("{id}")
    public void update(@RequestBody @Valid UserDto user, @PathVariable long id) {

        userService.updateUserInfo(user, id);
    }

    /**
     * 세션을 이용한 로그아웃 기능.
     *
     */
    @PostMapping("logout")
    public void logout() {

        loginService.logout();
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id, String password) {

        userService.deleteUser(id, password);
    }
}