package com.ludensdomain.controller;

import com.ludensdomain.dto.GameDto;
import com.ludensdomain.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/{gameId}")
    public void buyGame(@PathVariable long gameId, long userId) {

    }

    @PostMapping("/{userId}")
    public void chargeFund(@PathVariable long userId, int amount) {

    }

    @GetMapping("/{userId}")
    public List<GameDto> getPurchaseHistory(@PathVariable long userId) {

        return purchaseService.getPurchaseHistory(userId);
    }

    @PutMapping("/{gameId}")
    public void refund(@PathVariable long gameId, long userId) {

    }
}
