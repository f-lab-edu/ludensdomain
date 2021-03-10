package com.ludensdomain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GamePagingDto {

    int lastGameId;

    int size;

    String developer;

    String publisher;

    String genre;

}
