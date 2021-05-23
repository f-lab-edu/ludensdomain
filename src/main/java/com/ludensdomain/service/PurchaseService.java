package com.ludensdomain.service;

import com.ludensdomain.dto.GameDto;
import com.ludensdomain.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;

    public void buyGame(long gameId, long userId) {

    }

    public List<GameDto> getPurchaseHistory(long userId) {

        return purchaseMapper.getPurchaseHistory(userId);
    }

    public void refund(long gameId, long userId) {

    }
}
