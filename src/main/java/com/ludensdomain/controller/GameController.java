package com.ludensdomain.controller;

import com.ludensdomain.domain.AuthLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_BAD_REQUEST;
import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    AuthLevel authLevel;

    @GetMapping("/{gameId}")
    public ResponseEntity<Void> selectGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @GetMapping("/gameList")
    public ResponseEntity<Void> selectGameList() {

        return RESPONSE_OK;
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<Void> insertNewGame(@PathVariable String gameId) {
        if (authLevel == AuthLevel.ADMIN) {
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateGameInfo(@PathVariable String gameId) {
        if (authLevel == AuthLevel.COMPANY) {
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable String gameId) {
        if (authLevel == AuthLevel.ADMIN) {
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }
}
