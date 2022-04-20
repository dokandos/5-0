package com.ScoreReceiver.DTOs;

import com.ScoreReceiver.domain.UserScore;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.ScoreTimeCreationException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserScoreDTO {

    private long matchId;
    private int homeTeamScore;
    private int awayTeamScore;

    public UserScore mapToUserScore(){
        return new UserScore(matchId, homeTeamScore, awayTeamScore);
    }
}
