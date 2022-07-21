package com.ScoreReceiver.controllers;


import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.UserScore;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.NoSuchMatchException;
import com.ScoreReceiver.errors.NoSuchScoreException;
import com.ScoreReceiver.errors.ScoreUpsertTimeException;
import com.ScoreReceiver.service.UserScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ExceptionHandler()//TODO complete exception handler
    public void createNewScore(@RequestBody UserScoreDTO userScoreDTO, @RequestParam long userId)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        userScoreService.createNewScore(userScoreDTO, userId);
    }

    @PutMapping()
    //TODO Figure out what was the second parameter
    public ResponseEntity<UserScore> modifyScore(@RequestBody UserScoreDTO userScoreDTO, @PathVariable String scoreId)
            throws NoSuchScoreException, NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        return new ResponseEntity<>(userScoreService.modifyScore(userScoreDTO, scoreId), HttpStatus.CREATED);
    }

    //TODO implement create and modify for multiple scores.
    @PostMapping
    //TODO is it better to throw an exception or answer with a 400 error code???
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewScores(@RequestBody List<UserScoreDTO> userScoresDTO, @RequestParam long userId)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        userScoreService.createNewScores(userScoresDTO, userId);
    }

    @PutMapping()
    public ResponseEntity<List<UserScore>> modifyScores(@RequestBody List<UserScoreDTO> userScoresDTO, @RequestParam long userId)
            throws NoSuchScoreException, NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        return new ResponseEntity<>(userScoreService.modifyScores(userScoresDTO, userId), HttpStatus.OK);
    }
}
