package com.ludensdomain.service;

import com.ludensdomain.advice.exceptions.UpdateFailedException;
import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.mapper.GameMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameMapper gameMapper;

    public GameDto getGameInfo(long gameId) {

        return gameMapper.getGameInfo(gameId);
    }

    @Transactional(readOnly = true)
    public List<GameDto> getGameList(GamePagingDto listInfo) {

        return gameMapper.getGameList(listInfo);
    }

    public void insertGame(GameDto gameDto) {

        gameMapper.insertGame(gameDto);
    }

    public void updateGame(long gameId, GameDto game) {
        Optional<GameDto> checkGame = Optional.ofNullable(getGameInfo(gameId));

        if(checkGame.isPresent()) {
            GameDto updatedGame = buildGame(gameId, game);
            gameMapper.updateGame(updatedGame);
        } else {
            String message = "게임 아이디[" + gameId + "] 업데이트에 실패했습니다.";
            throw new UpdateFailedException(message);
        }
    }

    public GameDto buildGame(long gameId, GameDto game) {
        return GameDto.builder()
                .id(gameId)
                .title(game.getTitle())
                .genre(game.getGenre())
                .description(game.getDescription())
                .releaseDate(game.getReleaseDate())
                .price(game.getPrice())
                .developer(game.getDeveloper())
                .publisher(game.getPublisher())
                .status(1)
                .build();
    }

    public void updateGameStatus(long gameId, int status) {

        gameMapper.updateGameStatus(gameId, status);
    }

    /**
     * 게임 삭제 기능
     * 게임을 삭제하고 싶으면 먼저 updateGameStatus()로 게임 판매 여부를 4(게임 삭제)로 변경
     * 그 다음 젠킨스를 이용해 매일 정해진 시간에 deleteSchedule()을 실행해 게임 판매 여부가 4인 값을 삭제
     * 서버가 다운 되거나 삭제 도중 에러로 중단된 경우에 재실행이 되도록 젠킨스로 실행하고 관리할 예정
     */
    @Transactional
    public void deleteGame() {

        gameMapper.deleteGame();
    }
}
