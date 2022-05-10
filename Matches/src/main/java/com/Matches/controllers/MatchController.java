package com.Matches.controllers;

import com.Matches.domain.Match;
import com.Matches.infrastructure.TournamentRepository;
import com.Matches.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Match getSingleMatch(@PathVariable long matchId) {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Match createMatch(@RequestBody Match match) {

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Match modifyMatch(@RequestBody Match match) {

    }

}
