package com.ludensdomain.controller;

import com.ludensdomain.aop.AuthLevel;
import com.ludensdomain.aop.LoginCheck;
import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ludensdomain.util.RedisCacheKeyConstants.GAME_LIST;
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

    @Cacheable(key = "#listInfo", value = GAME_LIST, cacheManager = "redisCacheManager")
    @LoginCheck(authLevel = AuthLevel.USER)
    @GetMapping("/gameList")
    public List<GameDto> selectGameList(GamePagingDto listInfo) {

        return gameService.getGameList(listInfo);
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
