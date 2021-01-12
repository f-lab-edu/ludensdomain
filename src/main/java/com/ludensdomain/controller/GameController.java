package com.ludensdomain.controller;

import com.ludensdomain.aop.CompanyCheck;
import com.ludensdomain.aop.UserCheck;
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

    @UserCheck
    @GetMapping("/{gameId}")
    public ResponseEntity<Void> selectGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @UserCheck
    @GetMapping("/gameList")
    public ResponseEntity<Void> selectGameList() {

        return RESPONSE_OK;
    }

    @CompanyCheck
    @PostMapping("/{gameId}")
    public ResponseEntity<Void> insertNewGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @CompanyCheck
    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateGameInfo(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @CompanyCheck
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }
}
