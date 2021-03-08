package com.ludensdomain.mapper;

import com.ludensdomain.dto.GameDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {

    List<GameDto> getGameList(int startPoint);

}
