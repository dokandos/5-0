package com.ScoreReceiver.service;

import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.Match;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.infrastructure.MatchRepository;
import com.ScoreReceiver.infrastructure.UserScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserScoreServiceTest {
    private Random random;

    @Mock private UserScoreRepository userScoreRepository;
    @Mock private MatchRepository matchRepository;
    private UserScoreService userScoreService;
    private Match match;

    @BeforeEach
    void setUp() {
        random = new Random();
        userScoreService = new UserScoreService(userScoreRepository, matchRepository);
        match = new Match("Colombia", "Argentina", null);
    }

    @Test
    void itShouldCreateUserScore(){

        when(matchRepository.findById(0L)).thenReturn(Optional.of(match));

        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(15);
        int randomAwayTeamScore = random.nextInt(15);

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO userScoreDTO = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore);
            userScoreService.createNewScore(userScoreDTO);
        });

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO userScoreDTO = new UserScoreDTO(randomMatchId, 0, 0);
            userScoreService.createNewScore(userScoreDTO);
        });

        verify(matchRepository, times(2)).findById(randomMatchId);
        verify(userScoreRepository, times(2)).save(any());
    }

    //Split into different tests?
    @Test
    void itShouldNotCreateUserScoreDueToNegativeScores(){
        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(14)*(-1)+1; //Negative
        int randomAwayTeamScore = random.nextInt(14)*(-1)+1; //Negative

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, 0);
            userScoreService.createNewScore(underTest);
        }).isInstanceOf(IllegalTeamScoreException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, 0, randomAwayTeamScore);
            userScoreService.createNewScore(underTest);
        }).isInstanceOf(IllegalTeamScoreException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore);
            userScoreService.createNewScore(underTest);
        }).isInstanceOf(IllegalTeamScoreException.class);
    }

    @Test
    @Disabled
    void itShouldReturnCorrectWinner() {

    }
}