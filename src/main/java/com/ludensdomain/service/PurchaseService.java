package com.ludensdomain.service;

import com.ludensdomain.dto.PurchaseDto;
import com.ludensdomain.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
@Log4j2
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;

    public void buy(long purchaseId, long userId, long gameId) {
        PurchaseDto purchaseInfo = buildPurchase(purchaseId, userId, gameId);
        purchaseMapper.buy(purchaseInfo);
    }

    public void refund(long purchaseId) {

        purchaseMapper.refund(purchaseId);
    }

    private PurchaseDto buildPurchase(long purchaseId, long userId, long gameId) {
        return PurchaseDto.builder()
                .purchaseId(purchaseId)
                .userId(userId)
                .gameId(gameId)
                .refund(0)
                .purchaseDate(now())
                .build();
    }
}
