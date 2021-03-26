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

@Log4j2
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameMapper gameMapper;

    public GameDto getGameInfo(long gameId) {

        return gameMapper.getGameInfo(gameId);
    }

    public List<GameDto> getGameList(GamePagingDto listInfo) {

        return gameMapper.getGameList(listInfo);
    }

    public void insertGame(GameDto gameDto) {

        gameMapper.insertGame(gameDto);
    }

    public void updateGame(long id, GameDto game) {
        Optional<GameDto> checkGame = Optional.ofNullable(getGameInfo(id));

        if(checkGame.isPresent()) {
            GameDto updatedGame = buildGame(id, game);
            gameMapper.updateGame(updatedGame);
        } else {
            String message = "게임 아이디[" + id + "] 업데이트에 실패했습니다.";
            throw new UpdateFailedException(message);
        }
    }

    public GameDto buildGame(long id, GameDto game) {
        return GameDto.builder()
                .id(id)
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

}
