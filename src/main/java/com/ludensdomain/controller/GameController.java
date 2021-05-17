package com.ludensdomain.controller;

import com.ludensdomain.advice.exceptions.InsertFailedException;
import com.ludensdomain.aop.AuthLevel;
import com.ludensdomain.aop.LoginCheck;
import com.ludensdomain.aop.RoleCheck;
import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ludensdomain.util.RedisCacheKeyConstants.GAME_LIST;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Log4j2
public class GameController {

    private final GameService gameService;

    @LoginCheck
    @GetMapping("/{gameId}")
    public GameDto selectGame(@PathVariable long gameId) {

        return gameService.getGameInfo(gameId);
    }

    /*
     * @Cacheable : 캐시 적용. RedisConfig로 정의한 redisCacheManager 기반으로 listInfo를 키로 저장
     */
    @Cacheable(key = "#listInfo", value = GAME_LIST, cacheManager = "redisCacheManager")
    @LoginCheck
    @GetMapping("/")
    public List<GameDto> selectGameList(@Valid GamePagingDto listInfo) {

        return gameService.getGameList(listInfo);
    }

    @RoleCheck(authLevel = AuthLevel.COMPANY)
    @PostMapping("/game")
    public void insertNewGame(GameDto gameDto) {

        gameService.insertGame(gameDto);
    }

    /*
     * 해당 메서드는 배급사에서 이미 등록된 게임의 정보를 수정하는 용도가 주다.
     * 게임의 평점이나 판매수는 다른 메서드로 구현
     * @LoginCheck : aop로 사용자 식별 및 인가
     */
    @RoleCheck(authLevel = AuthLevel.COMPANY)
    @PutMapping("/{gameId}")
    public void updateGameInfo(@PathVariable long gameId, GameDto gameDto) {

        gameService.updateGame(gameId, gameDto);
    }

    @RoleCheck(authLevel = AuthLevel.ADMIN)
    @PatchMapping("/{gameId}")
    public void updateGameStatus(@PathVariable long gameId, int status) {

        gameService.updateGameStatus(gameId, status);
    }

    // Jenkins에서 deleteGame 에러 발생 시 확인용 호출하기 위해 필요한 메서드
    @DeleteMapping("/game")
    public void deleteGame(@RequestParam boolean scheduled) {
        if(scheduled) {
            gameService.deleteGame();
        }
    }
}
