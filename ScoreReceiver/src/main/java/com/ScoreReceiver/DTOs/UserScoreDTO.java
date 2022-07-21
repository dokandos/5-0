package com.ScoreReceiver.DTOs;

import com.ScoreReceiver.domain.ScoreIdGenerator;
import com.ScoreReceiver.domain.UserScore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserScoreDTO{

    private long matchId;
    private int homeTeamScore;
    private int awayTeamScore;
    private Timestamp timestamp;

    public UserScoreDTO(long matchId, int homeTeamScore, int awayTeamScore) {
        this.matchId = matchId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public UserScore mapToUserScore(long userId){
        return new UserScore(userId, matchId, homeTeamScore, awayTeamScore, timestamp);
    }
}
