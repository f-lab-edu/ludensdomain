package com.ludensdomain.service;

import com.ludensdomain.advice.exceptions.InsertFailedException;
import com.ludensdomain.advice.exceptions.UpdateFailedException;
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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
    GameDto updatedGame;
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

        updatedGame = GameDto
                .builder()
                .id(ID)
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
                .developer(1)
                .publisher(1)
                .genre(1)
                .build();
    }

    @Test
    @DisplayName("게임 아이디를 입력해 게임 아이디가 매칭되는 게임 정보를 불러온다.")
    public void getGameInfoSuccess() {
        when(gameMapper.getGameInfo(ID)).thenReturn(game);
        gameService.getGameInfo(ID);
        verify(gameMapper).getGameInfo(ID);
    }

    @Test
    @DisplayName("페이징 정보를 입력해 게임 리스트를 불러온다.")
    public void getGameListSuccess() {
        when(gameMapper.getGameList(paging)).thenReturn(anyList());
        gameService.getGameList(paging);
        verify(gameMapper).getGameList(paging);
    }

    @Test
    @DisplayName("게임 정보를 입력하면 새로운 게임 정보를 입력한다.")
    public void insertGameSuccess() {
        doNothing().when(gameMapper).insertGame(game);
        gameService.insertGame(game);
        verify(gameMapper).insertGame(game);
    }

    @Test
    @DisplayName("게임 정보를 입력하는 도중 실패했을 때 InsertFailedException이 발생한다.")
    public void insertGameFailed() {
        doThrow(InsertFailedException.class).when(gameMapper).insertGame(game);
        assertThrows(InsertFailedException.class, ()-> gameService.insertGame(game));
        verify(gameMapper).insertGame(game);
    }

    @Test
    @DisplayName("게임 아이디 중복 여부를 확인하고 중복된다면 true를 반환한다.")
    public void isGameExistsReturnTrue() {
        when(gameMapper.isGameExists(ID)).thenReturn(true);
        assertTrue(gameService.isGameExists(ID));
        verify(gameMapper).isGameExists(ID);
    }

    @Test
    @DisplayName("게임 아이디 중복 여부를 확인하고 중복되지 않는다면 false를 반환한다.")
    public void isGameExistsReturnFalse() {
        when(gameMapper.isGameExists(ID)).thenReturn(false);
        assertFalse(gameService.isGameExists(ID));
    }

    @Test
    @DisplayName("입력한 게임 아이디가 중복되지 않는다면 게임 정보를 업데이트한다.")
    public void updateGameSuccess() {
        when(gameMapper.isGameExists(ID)).thenReturn(false);
        assertFalse(gameService.isGameExists(ID));

        gameService.updateGame(ID, updatedGame);
        verify(gameMapper).updateGame(any(GameDto.class));
    }

    @Test
    @DisplayName("입력한 게임 아이디가 중복된다면 UpdateFailedException이 발생한다.")
    public void updateGameFailByDuplicatedGameId() {
        when(gameMapper.isGameExists(ID)).thenReturn(true);
        assertThrows(UpdateFailedException.class, () -> gameService.updateGame(ID, game));
        verify(gameMapper).isGameExists(ID);
    }

    @Test
    @DisplayName("게임 아이디를 입력해 매칭 되는 게임의 상태를 업데이트한다.")
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
