package com.ludensdomain.controller;

import com.ludensdomain.aop.LoginCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    @LoginCheck(authLevel = LoginCheck.AuthLevel.USER)
    @GetMapping("/{gameId}")
    public ResponseEntity<Void> selectGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = LoginCheck.AuthLevel.USER)
    @GetMapping("/gameList")
    public ResponseEntity<Void> selectGameList() {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = LoginCheck.AuthLevel.COMPANY)
    @PostMapping("/{gameId}")
    public ResponseEntity<Void> insertNewGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = LoginCheck.AuthLevel.COMPANY)
    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateGameInfo(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = LoginCheck.AuthLevel.COMPANY)
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }
}
