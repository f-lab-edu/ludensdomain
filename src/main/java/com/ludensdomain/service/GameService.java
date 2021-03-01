package com.ludensdomain.service;

import com.ludensdomain.dto.GameDto;
import com.ludensdomain.mapper.GameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameMapper gameMapper;

    public List<GameDto> getGameList() {

        return gameMapper.getGameList();
    }

}
