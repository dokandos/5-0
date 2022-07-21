package com.ScoreReceiver.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserScoreTest {

    private UserScore homeWinner, awayWinner, tied;


    @BeforeEach
    void setUp() {
        long userId  = new Random().nextLong();
        long matchId  = new Random().nextLong();
        homeWinner = new UserScore(userId, matchId, 2,1);
        awayWinner = new UserScore(userId, matchId, 4, 5);
        tied = new UserScore(userId, matchId, 0,0);
    }

    @Test
    void itShouldSetHomeTeamAsWinner() {
        assertThat(homeWinner.getWinnerTeam()).isEqualTo(Winner.HOME);
    }

    @Test
    void itShouldSetAwayTeamAsWinner() {
        assertThat(awayWinner.getWinnerTeam()).isEqualTo(Winner.AWAY);
    }

    @Test
    void itShouldSetTieAsWinner() {
        assertThat(tied.getWinnerTeam()).isEqualTo(Winner.TIE);
    }
}
