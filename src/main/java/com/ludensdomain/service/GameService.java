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
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.HOURS;

@Log4j2
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameMapper gameMapper;
    private final ScheduledExecutorService scheduler;

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
                .status(game.getStatus())
                .build();
    }

    public void updateGameStatus(long gameId, int status) {

        gameMapper.updateGameStatus(gameId, status);
    }

    public void deleteGame(long gameId) {
        updateGameStatus(gameId, 4);
        Runnable task = gameMapper.deleteGame(gameId);
        scheduler.schedule(task, 12, HOURS);
    }
}
