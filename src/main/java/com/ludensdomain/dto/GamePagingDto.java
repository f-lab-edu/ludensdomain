package com.ludensdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class GamePagingDto {

    @NotNull
    private int lastGameId;

    @NotNull
    private int size;

    @NotNull
    private long developer;

    @NotNull
    private long publisher;

    @NotNull
    private long genre;

}
