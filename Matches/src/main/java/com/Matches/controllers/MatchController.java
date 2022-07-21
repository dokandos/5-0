package com.Matches.controllers;

import com.Matches.domain.Match;
import com.Matches.errors.NoSuchMatchException;
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
    public Match getSingleMatch(@PathVariable long matchId) throws NoSuchMatchException {
        return matchService.getMatchById(matchId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMatch(@RequestBody Match match) {
        matchService.createNewMatch(match);
    }

//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Match modifyMatch(@RequestBody Match match) {
//
//    }

}
