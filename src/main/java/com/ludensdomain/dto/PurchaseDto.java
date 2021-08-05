package com.ludensdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class PurchaseDto {

    @NotNull
    private long purchaseId;

    @NotNull
    private long userId;

    @NotNull
    private long gameId;

    @NotNull
    private int refund;

    @NotNull
    private Date purchaseDate;

}
