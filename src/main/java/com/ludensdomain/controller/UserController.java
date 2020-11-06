package com.ludensdomain.controller;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_NO_CONTENT;
import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@Valid long id, String password) {
        ResponseEntity<Void> result;
        Optional<UserDto> user = userService.getUserInfo(id, password);

        if (!user.isPresent()) {
            result = RESPONSE_NO_CONTENT;
        } else {
            result = RESPONSE_OK;
        }
        return result;
    }

    @PostMapping("signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDto) {
        userService.insertUserInfo(userDto);

        return RESPONSE_OK;
    }
}