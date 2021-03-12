package com.ludensdomain.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class GameDto {

    @Id
    @NotNull @NotEmpty(message = "게임 아이디를 입력하세요.")
    long id;

    @NotNull @NotEmpty(message = "게임 타이틀을 입력하세요.")
    String title;

    @NotNull @NotEmpty(message = "게임 장르를 입력하세요.")
    String genre;

    String description;

    Date releaseDate;

    int price;

    @NotEmpty(message = "개발사를 입력하세요.")
    long developer;

    @NotEmpty(message = "배급사를 입력하세요.")
    long publisher;

    int rating;

    int sales;

    int status;

}
