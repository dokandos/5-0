package com.ScoreReceiver.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String homeTeam;
    private String awayTeam;
    private Timestamp timestamp;
    private Winner winner;

    public Match(String homeTeam, String awayTeam, Timestamp timestamp){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.timestamp = timestamp;
    }
}
