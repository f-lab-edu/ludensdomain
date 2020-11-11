package com.ludensdomain.controller;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static com.ludensdomain.util.ResponseEntityConstants.*;

@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
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

    @PostMapping("signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto user) {
        userService.insertUserInfo(user);

        return RESPONSE_USER_CREATED;
    }
}