package com.ludensdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class GameDto {

    @Id
    @NotNull @NotEmpty(message = "게임 아이디를 입력하세요.")
    private long id;

    @NotNull @NotEmpty(message = "게임 타이틀을 입력하세요.")
    private String title;

    @NotNull @NotEmpty(message = "게임 장르를 입력하세요.")
    private String genre;

    private String description;

    private LocalDate releaseDate;

    private int price;

    @NotEmpty(message = "개발사를 입력하세요.")
    private long developer;

    @NotEmpty(message = "배급사를 입력하세요.")
    private long publisher;

    private double rating;

    private int sales;

    private int status;

    private String genreName;

    private String publisherName;

    private String developerName;

    private String statusName;

}
