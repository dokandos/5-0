package com.ScoreReceiver.service;

import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.Match;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.ScoreUpsertTimeException;
import com.ScoreReceiver.infrastructure.MatchRepository;
import com.ScoreReceiver.infrastructure.UserScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserScoreServiceTest {
    private Random random;

    @Mock private UserScoreRepository userScoreRepository;
    @Mock private MatchRepository matchRepository;
    private UserScoreService userScoreService;
    private Match match;
    private Timestamp matchTime;
//    @Captor
//    ArgumentCaptor<UserScore> userScoreCaptor;

    @BeforeEach
    void setUp() {
        random = new Random();
        matchTime = new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7));
        userScoreService = new UserScoreService(userScoreRepository, matchRepository);
        match = new Match("Colombia", "Argentina", matchTime);
    }

    @Test
    void itShouldCreateUserScore(){

        when(matchRepository.findById(0L)).thenReturn(Optional.of(match));

        long randomUserId = 0L;
        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(15);
        int randomAwayTeamScore = random.nextInt(15);

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO userScoreDTO = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore);
            userScoreService.createNewScore(userScoreDTO, randomUserId);
        });

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO userScoreDTO = new UserScoreDTO(randomMatchId, 0, 0);
            userScoreService.createNewScore(userScoreDTO, randomUserId);
        });

        verify(matchRepository, times(2)).findById(randomMatchId);
        verify(userScoreRepository, times(2)).save(any());
    }

    //Split into different tests?
    @Test
    void itShouldNotCreateUserScoreDueToNegativeScores(){
        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(14)*(-1)-1; //Negative
        int randomAwayTeamScore = random.nextInt(14)*(-1)-1; //Negative

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, 0);
            userScoreService.validateTeamScores(underTest);
        }).isInstanceOf(IllegalTeamScoreException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, 0, randomAwayTeamScore);
            userScoreService.validateTeamScores(underTest);
        }).isInstanceOf(IllegalTeamScoreException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore);
            userScoreService.validateTeamScores(underTest);
        }).isInstanceOf(IllegalTeamScoreException.class);
    }

    @Test
    void itShouldThrowExceptionWhenScoreUpsertCloseOrAfterMatchTime() {
        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(14);
        int randomAwayTeamScore = random.nextInt(14);

        Timestamp limitPlusFiveMinutes = new Timestamp(matchTime.getTime() - TimeUnit.MINUTES.toMillis(UserScoreService.LIMIT_BEFORE_MATCH_MINUTES-5));
        Timestamp limitPlusOneMinute = new Timestamp(matchTime.getTime() - TimeUnit.MINUTES.toMillis(UserScoreService.LIMIT_BEFORE_MATCH_MINUTES-1));
        Timestamp fiveMinutesAfterMatch = new Timestamp(matchTime.getTime() + TimeUnit.MINUTES.toMillis(5));
        Timestamp nextDayAfterMatch = new Timestamp(matchTime.getTime() + TimeUnit.DAYS.toMillis(1));

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, limitPlusFiveMinutes);
            userScoreService.validateTimeOfUpsert(underTest, match);
        }).isInstanceOf(ScoreUpsertTimeException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, limitPlusOneMinute);
            userScoreService.validateTimeOfUpsert(underTest, match);
        }).isInstanceOf(ScoreUpsertTimeException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, fiveMinutesAfterMatch);
            userScoreService.validateTimeOfUpsert(underTest, match);
        }).isInstanceOf(ScoreUpsertTimeException.class);

        assertThatThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, nextDayAfterMatch);
            userScoreService.validateTimeOfUpsert(underTest, match);
        }).isInstanceOf(ScoreUpsertTimeException.class);
    }

    @Test
    void itShouldNotThrowExceptionWhenScoreUpsertBeforeTimeLimitMatch() {
        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(14);
        int randomAwayTeamScore = random.nextInt(14);

        Timestamp limitMinusFiveMinutes = new Timestamp(matchTime.getTime() - TimeUnit.MINUTES.toMillis(UserScoreService.LIMIT_BEFORE_MATCH_MINUTES+5));
        Timestamp limitMinusOneMinute = new Timestamp(matchTime.getTime() - TimeUnit.MINUTES.toMillis(UserScoreService.LIMIT_BEFORE_MATCH_MINUTES+1));
        Timestamp oneDayBeforeMatch = new Timestamp(matchTime.getTime() - TimeUnit.DAYS.toMillis(1));

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, limitMinusFiveMinutes);
            userScoreService.validateTimeOfUpsert(underTest, match);
        });

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, limitMinusOneMinute);
            userScoreService.validateTimeOfUpsert(underTest, match);
        });

        assertThatNoException().isThrownBy(() -> {
            UserScoreDTO underTest = new UserScoreDTO(randomMatchId, randomHomeTeamScore, randomAwayTeamScore, oneDayBeforeMatch);
            userScoreService.validateTimeOfUpsert(underTest, match);
        });

    }
/*    @Test
    @Disabled
    void itShouldReturnCorrectWinner()
            throws IllegalTeamScoreException, NoSuchMatchException, ScoreTimeCreationException {

        when(matchRepository.findById(0L)).thenReturn(Optional.of(match));

        long randomMatchId = 0L;
        int randomHomeTeamScore = random.nextInt(14)+1;
        int randomAwayTeamScore = random.nextInt(14)+1;

        UserScoreDTO homeWinner = new UserScoreDTO(randomMatchId, randomHomeTeamScore, 0);
        UserScoreDTO awayWinner = new UserScoreDTO(randomMatchId, 0, randomAwayTeamScore);
        UserScoreDTO tied = new UserScoreDTO(randomMatchId, 3, 3);

       userScoreService.createNewScore(homeWinner);
       verify(userScoreRepository).save(userScoreCaptor.capture());
       UserScore userScore = userScoreCaptor.getValue();
       assertThat(userScore.getWinnerTeam()).isEqualTo(Winner.HOME);

    }*/
}