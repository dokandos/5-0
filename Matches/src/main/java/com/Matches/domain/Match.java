package com.Matches.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long matchId;
    @ManyToOne()
    @JoinColumn(name="tournamentId", nullable = false)
    private Tournament tournament;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Timestamp timestamp;
    private boolean finished;

    public Match(Tournament tournament, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, Timestamp timestamp, boolean finished) {
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.timestamp = timestamp;
        this.finished = finished;
    }
}
