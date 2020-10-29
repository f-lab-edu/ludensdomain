package com.ludensdomain.controller;

import com.ludensdomain.dto.UserDto;
import com.ludensdomain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login") // 로그인 화면으로 이동
    public String login() { return "/user/loginForm"; }

    @PostMapping("loginProc") // 로그인 프로세스
    public ResponseEntity<?> loginProc(UserDto userDto, HttpServletRequest req) {

        HttpSession httpSession = req.getSession();
        UserDto checkUserDto = userService.getUserInfo(userDto);

        if( httpSession != null ){
            if( userDto.getId().equals(checkUserDto.getId()) && userDto.getPassword().equals(checkUserDto.getPassword()) ) {
                httpSession.setAttribute("dto", userDto);

                return new ResponseEntity<>("success", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("fail", HttpStatus.OK);
    }

    @GetMapping("signUp")
    public String signIn() { return "/user/signUpForm"; }

    @PostMapping("signUpProc")
    public String signInProc() {


        return "";
    }

}
