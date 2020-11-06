package com.ludensdomain.controller;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_NO_CONTENT;
import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDto userDto, HttpServletRequest req) {

        HttpSession httpSession = req.getSession();
        UserDto checkUserDto = userService.getUserInfo(userDto);

        if( httpSession != null ){
            if( userDto.getId().equals(checkUserDto.getId()) && userDto.getPassword().equals(checkUserDto.getPassword()) ) {
                httpSession.setAttribute("dto", userDto);

                return RESPONSE_OK;
            }
        }

        return RESPONSE_NO_CONTENT;
    }

    @PostMapping("signUp")
    public String signUp(@RequestBody UserDto userDto) {
        userService.insertUserInfo(userDto);

        return "/user/login";
    }
}