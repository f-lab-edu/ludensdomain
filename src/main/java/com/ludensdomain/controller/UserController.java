package com.ludensdomain.controller;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.UserService;
import com.ludensdomain.util.ResponseEntityStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> loginProc(@RequestBody UserDto userDto, HttpServletRequest req) {

        HttpSession httpSession = req.getSession();
        UserDto checkUserDto = userService.getUserInfo(userDto);

        if( httpSession != null ){
            if( userDto.getId().equals(checkUserDto.getId()) && userDto.getPassword().equals(checkUserDto.getPassword()) ) {
                httpSession.setAttribute("dto", userDto);

                return ResponseEntityStatus.SUCCESS;
            }
        }

        return ResponseEntityStatus.FAIL;
    }

    @PostMapping("signUp")
    public String signUp(@RequestBody UserDto userDto) {
        userService.insertUserInfo(userDto);

        return "/user/login";
    }
}