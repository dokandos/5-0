package com.Matches.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private long id;
    private String name;
    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;
    private boolean finished;
}
