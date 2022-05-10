package com.Matches.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Tournament {
    @Id
    private long tournamentId;
    private String name;
    @OneToMany(mappedBy = "matchId")
    private List<Match> matches;
    private boolean finished;
}
