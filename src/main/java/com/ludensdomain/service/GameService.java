package com.ludensdomain.service;

import com.ludensdomain.advice.exceptions.UpdateFailedException;
import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.mapper.GameMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.HOURS;

@Log4j2
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameMapper gameMapper;
    private final ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();

    public GameDto getGameInfo(long gameId) {

        return gameMapper.getGameInfo(gameId);
    }

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
            log.error("Error updating game[" + gameId + "]");
            throw new UpdateFailedException();
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
     * 지정한 게임 판매 상태를 판매 불가(4)로 만든 후 12시간 뒤에 게임을 삭제하는 기능
     * @param gameId 게임 아이디
     * newSingleThreadScheduledExecutor : 싱글 쓰레드 스케줄러로 한 번에 하나의 스케줄만 실행 가능
     */
    public void deleteGame(long gameId) {
        updateGameStatus(gameId, 4);
        schedule.schedule(gameMapper.deleteGame(gameId), 12, HOURS);
    }

    public void checkGameDeleted() {

    }
}
