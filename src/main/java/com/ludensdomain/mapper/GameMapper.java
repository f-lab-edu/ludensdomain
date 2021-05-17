package com.ludensdomain.mapper;

import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {

    GameDto getGameInfo(long gameId);

    List<GameDto> getGameList(GamePagingDto listInfo);

    void insertGame(GameDto gameDto);

    boolean isGameExists(long gameId);

    void updateGame(GameDto gameDto);

    void updateGameStatus(long gameId, int status);

    void deleteGame();

}
