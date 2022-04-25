package com.ScoreReceiver.controllers;


import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.UserScore;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.NoSuchMatchException;
import com.ScoreReceiver.errors.ScoreUpsertTimeException;
import com.ScoreReceiver.service.UserScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/UserScore")
@AllArgsConstructor
public class UserScoreController{

    private final UserScoreService userScoreService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserScore> getScoresForMatch(long matchId) {
        return userScoreService.getScoresForMatch(matchId);
    }

    @PostMapping
    //TODO is it better to throw an exception or answer with a 400 error code???
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewScore(@RequestBody UserScoreDTO userScoreDTO)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        userScoreService.createNewScore(userScoreDTO);
    }
}
