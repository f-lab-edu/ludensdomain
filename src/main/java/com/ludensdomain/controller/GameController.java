package com.ludensdomain.controller;

import com.ludensdomain.aop.UserCheck;
import com.ludensdomain.domain.AuthLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    @UserCheck(role = AuthLevel.USER)
    @GetMapping("/{gameId}")
    public ResponseEntity<Void> selectGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @UserCheck(role = AuthLevel.USER)
    @GetMapping("/gameList")
    public ResponseEntity<Void> selectGameList() {

        return RESPONSE_OK;
    }

    @UserCheck(role = AuthLevel.COMPANY)
    @PostMapping("/{gameId}")
    public ResponseEntity<Void> insertNewGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @UserCheck(role = AuthLevel.COMPANY)
    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateGameInfo(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @UserCheck(role = AuthLevel.COMPANY)
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }
}
