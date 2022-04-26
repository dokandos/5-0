package com.ScoreReceiver.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId; //TODO Replace with User?

//    @ManyToOne
    private long matchId;

    private int homeTeamScore;
    private int awayTeamScore;
    private int difference;
    private Winner winnerTeam;
    private Timestamp timestamp;

    public UserScore(long matchId, int homeTeamScore, int awayTeamScore, Timestamp timestamp) {
        this.matchId = matchId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.timestamp = timestamp;
        this.difference = Math.abs(homeTeamScore-awayTeamScore);
        setWinnerTeamFromScores(homeTeamScore, awayTeamScore);
    }

    public UserScore(long matchId, int homeTeamScore, int awayTeamScore) {
        this.matchId = matchId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.difference = Math.abs(homeTeamScore-awayTeamScore);
        setWinnerTeamFromScores(homeTeamScore, awayTeamScore);
    }

    private void setWinnerTeamFromScores(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore>awayTeamScore)      winnerTeam = Winner.HOME;
        else if (homeTeamScore<awayTeamScore) winnerTeam = Winner.AWAY;
        else                                  winnerTeam = Winner.TIE;
    }


}
