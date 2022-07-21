package com.Matches.controllers;


import com.Matches.domain.Tournament;
import com.Matches.errors.NoSuchTournamentException;
import com.Matches.infrastructure.MatchRepository;
import com.Matches.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tournament")
@AllArgsConstructor
public class TournamentController {

    private final MatchService matchService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tournament getSingleTournament(@PathVariable long id) throws NoSuchTournamentException {
        return matchService.findTournamentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTournament(Tournament tournament) {
        matchService.createNewTournament(tournament);
    }

//    @PatchMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Tournament patchTournament(Tournament tournament) {
//
//    }

}
