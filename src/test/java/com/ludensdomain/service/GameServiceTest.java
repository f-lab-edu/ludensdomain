package com.ludensdomain.service;

import com.ludensdomain.dto.GameDto;
import com.ludensdomain.dto.GamePagingDto;
import com.ludensdomain.mapper.GameMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.util.DateUtil.now;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    public static final long ID = 1;

    @Mock
    GameMapper gameMapper;

    @InjectMocks
    GameService gameService;

    GameDto game;
    GamePagingDto paging;

    @BeforeEach
    void setUp() {
        game = GameDto
                .builder()
                .id(1)
                .title("게임명")
                .genre("장르")
                .description("설명")
                .releaseDate(now())
                .price(10000)
                .developer(1)
                .publisher(1)
                .rating(9)
                .sales(100)
                .status(3)
                .genreName("액션")
                .publisherName("우너브라더스")
                .developerName("우비소프트")
                .statusName("통과")
                .build();

        paging = GamePagingDto
                .builder()
                .lastGameId(5)
                .size(10)
                .developer("우비소프트")
                .publisher("우너브라더스")
                .genre("액션")
                .build();
    }

    @Test
    @DisplayName("입력한 게임 아이디의 게임 정보를 불러오는데 성공한다.")
    public void getGameInfoSuccess() {
        when(gameMapper.getGameInfo(ID)).thenReturn(game);
        gameService.getGameInfo(ID);
        verify(gameMapper).getGameInfo(ID);
    }

    @Test
    @DisplayName("페이징 정보를 입력하면 게임 리스트를 불러오는데 성공한다.")
    public void getGameListSuccess() {
        when(gameMapper.getGameList(paging)).thenReturn(anyList());
        gameService.getGameList(paging);
        verify(gameMapper).getGameList(paging);
    }

    @Test
    @DisplayName("새로운 게임 정보를 입력하는데 성공한다.")
    public void insertGameSuccess() {
        gameService.insertGame(game);
        verify(gameMapper).insertGame(game);
    }

    @Test
    @DisplayName("존재하는 게임 아이디라면 게임 정보를 업데이트하는데 성공한다.")
    public void updateGameSuccess() {

    }

    @Test
    @DisplayName("기존에 있던 게임의 상태를 업데이트하는데 성공한다.")
    public void updateGameStatusSuccess() {
        gameService.updateGameStatus(ID, 4);
        verify(gameMapper).updateGameStatus(ID, 4);
    }

    @Test
    @DisplayName("게임을 삭제한다.")
    public void deleteGameSuccess() {
        gameService.deleteGame();
        verify(gameMapper).deleteGame();
    }
}
