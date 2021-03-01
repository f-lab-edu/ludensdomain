package com.ludensdomain.controller;

import com.ludensdomain.aop.AuthLevel;
import com.ludensdomain.aop.LoginCheck;
import com.ludensdomain.dto.GameDto;
import com.ludensdomain.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ludensdomain.util.ResponseEntityConstants.RESPONSE_OK;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Log4j2
public class GameController {

    private final GameService gameService;

    @LoginCheck(authLevel = AuthLevel.USER)
    @GetMapping("/{gameId}")
    public ResponseEntity<Void> selectGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = AuthLevel.USER)
    @GetMapping("/gameList")
    public List<GameDto> selectGameList() {

        return gameService.getGameList();
    }

    @LoginCheck(authLevel = AuthLevel.COMPANY)
    @PostMapping("/{gameId}")
    public ResponseEntity<Void> insertNewGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = AuthLevel.COMPANY)
    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateGameInfo(@PathVariable String gameId) {

        return RESPONSE_OK;
    }

    @LoginCheck(authLevel = AuthLevel.ADMIN)
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable String gameId) {

        return RESPONSE_OK;
    }
}
