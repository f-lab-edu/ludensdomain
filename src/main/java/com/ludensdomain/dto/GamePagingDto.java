package com.ludensdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class GamePagingDto {

    int lastGameId;

    int size;

    long developer;

    long publisher;

    long genre;

}
