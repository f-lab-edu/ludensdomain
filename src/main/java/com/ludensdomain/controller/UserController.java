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

    @GetMapping("login")
    public String login() { return "/user/login"; }

    @PostMapping("loginProc")
    public ResponseEntity<?> loginProc(UserDto userDto, HttpServletRequest req) {

        HttpSession httpSession = req.getSession();

        if( httpSession != null ){
            httpSession.setAttribute("dto", userDto);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("signIn")
    public String signIn() { return "/user/signIn"; }

    @PostMapping("signInProc")
    public String signInProc() {

        return "";
    }

}
