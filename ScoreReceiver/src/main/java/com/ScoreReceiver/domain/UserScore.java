package com.ScoreReceiver.domain;

import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.ScoreTimeCreationException;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId; //Replace with User?

    @ManyToOne
    private long matchId;

    private int homeTeamScore;
    private int awayTeamScore;
    private int difference;
    private Winner winnerTeam;

    public UserScore(long matchId, int homeTeamScore, int awayTeamScore) {
        this.matchId = matchId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.difference = Math.abs(homeTeamScore-awayTeamScore);
        setWinnerTeamFromScores(homeTeamScore, awayTeamScore);
    }

    private void setWinnerTeamFromScores(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore>awayTeamScore)      winnerTeam = Winner.HOME;
        else if (homeTeamScore<awayTeamScore) winnerTeam = Winner.AWAY;
        else                                  winnerTeam = Winner.TIE;
    }


}
