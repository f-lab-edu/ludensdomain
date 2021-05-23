package com.ludensdomain.mapper;

import com.ludensdomain.dto.GameDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseMapper {

    void buyGame(long gameId, long userId);

    List<GameDto> getPurchaseHistory(long userId);

    void refund(long gameId, long userId);

}
