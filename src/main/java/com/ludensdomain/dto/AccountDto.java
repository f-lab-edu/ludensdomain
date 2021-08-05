package com.ludensdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class AccountDto {

    @NotNull
    private long id;

    @NotNull
    private int amount;

}
