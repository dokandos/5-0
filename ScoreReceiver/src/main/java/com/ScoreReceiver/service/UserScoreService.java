package com.ScoreReceiver.service;

import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.UserScore;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.NoSuchMatchException;
import com.ScoreReceiver.errors.ScoreTimeCreationException;
import com.ScoreReceiver.infrastructure.MatchRepository;
import com.ScoreReceiver.infrastructure.UserScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;
    private final MatchRepository matchRepository;

    public List<UserScore> getScoresForMatch(long matchId) {
        return userScoreRepository.findByMatchId();
    }

    public void createNewScore(UserScoreDTO userScoreDTO)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreTimeCreationException {
        long matchId = userScoreDTO.getMatchId();
        validateTeamScores(userScoreDTO);
        validateTimeOfCreation(userScoreDTO);
        matchRepository.findById(matchId).orElseThrow(() -> new NoSuchMatchException("Match id " + matchId  + " not found"));
        userScoreRepository.save(userScoreDTO.mapToUserScore());
    }

    public void validateTeamScores(UserScoreDTO userScoreDTO) throws IllegalTeamScoreException{
        if (userScoreDTO.getHomeTeamScore()<0||userScoreDTO.getAwayTeamScore()<0) throw new IllegalTeamScoreException("Teams score cannot be less than 0");
    }

    public void validateTimeOfCreation(UserScoreDTO userScoreDTO) throws ScoreTimeCreationException {
        //TODO implement error when new score is created less than 15 mins before match
    }

}
