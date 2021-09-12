package com.ludensdomain.controller;

import com.ludensdomain.dto.PurchaseDto;
import com.ludensdomain.service.ChargeService;
import com.ludensdomain.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
@Log4j2
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ChargeService chargeService;

    @PostMapping("/game")
    public void buy(@RequestParam long purchaseId, long userId, long gameId) {

        purchaseService.buy(purchaseId, userId, gameId);
    }

    @PostMapping("/{userId}")
    public void chargeCash(@PathVariable long userId, int amount) {

        chargeService.chargeCash(userId, amount);
    }

    @PutMapping("/{purchaseId}")
    public void refund(@PathVariable long purchaseId) {

        purchaseService.refund(purchaseId);
    }
}
