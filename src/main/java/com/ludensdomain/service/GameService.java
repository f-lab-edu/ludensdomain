package com.ludensdomain.service;

import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.mapper.GameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
