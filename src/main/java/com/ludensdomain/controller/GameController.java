package com.ludensdomain.controller;

import com.ludensdomain.advice.exceptions.InsertFailedException;
import com.ludensdomain.aop.AuthLevel;
import com.ludensdomain.aop.LoginCheck;
import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ludensdomain.util.RedisCacheKeyConstants.GAME_LIST;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Log4j2
public class GameController {

    private final GameService gameService;

    @LoginCheck(authLevel = AuthLevel.USER)
    @GetMapping("/{gameId}")
    public GameDto selectGame(@PathVariable long gameId) {

        return gameService.getGameInfo(gameId);
    }

    /*
     * @Cacheable : 캐시 적용. RedisConfig로 정의한 redisCacheManager 기반으로 listInfo를 키로 저장
     * @LoginCheck : aop로 사용자 식별 및 인가
     */
    @Cacheable(key = "#listInfo", value = GAME_LIST, cacheManager = "redisCacheManager")
    @LoginCheck(authLevel = AuthLevel.USER)
    @GetMapping("/")
    public List<GameDto> selectGameList(GamePagingDto listInfo) {

        return gameService.getGameList(listInfo);
    }

    @LoginCheck(authLevel = AuthLevel.COMPANY)
    @PostMapping("/game")
    public void insertNewGame(GameDto gameDto) {
        try {
            gameService.insertGame(gameDto);
        } catch(Exception e) {
            throw new InsertFailedException();
        }
    }

    /*
     * 해당 메서드는 배급사에서 이미 등록된 게임의 정보를 수정하는 용도가 주다.
     * 게임의 평점이나 판매수는 다른 메서드로 구현
     */
    @LoginCheck(authLevel = AuthLevel.COMPANY)
    @PutMapping("/{gameId}")
    public void updateGameInfo(@PathVariable long gameId, GameDto gameDto) {

        gameService.updateGame(gameId, gameDto);
    }

    @LoginCheck(authLevel = AuthLevel.ADMIN)
    @PostMapping("/{gameId}")
    public void deleteGame(@PathVariable long gameId) {

        gameService.updateGameStatus(gameId, 4);
    }

    // Jenkins에서 deleteGame 에러 발생 시 확인용 호출하기 위해 필요한 메서드
    @DeleteMapping("/game")
    public void deleteSchedule(@RequestParam boolean scheduled, long gameId) {

        if(scheduled) {
            gameService.deleteSchedule(gameId);
        }
    }
}
