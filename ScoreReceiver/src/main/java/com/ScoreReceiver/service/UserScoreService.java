package com.ScoreReceiver.service;

import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.Match;
import com.ScoreReceiver.domain.UserScore;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.NoSuchMatchException;
import com.ScoreReceiver.errors.ScoreUpsertTimeException;
import com.ScoreReceiver.infrastructure.MatchRepository;
import com.ScoreReceiver.infrastructure.UserScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;
    private final MatchRepository matchRepository;

    public List<UserScore> getScoresForMatch(long matchId) {
        return userScoreRepository.findByMatchId(matchId);
    }

    public void createNewScore(UserScoreDTO userScoreDTO)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        long matchId = userScoreDTO.getMatchId();
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new NoSuchMatchException("Match id " + matchId  + " not found"));
        validateTeamScores(userScoreDTO);
        validateTimeOfUpsert(userScoreDTO, match);
        userScoreRepository.save(userScoreDTO.mapToUserScore());
    }

    public void validateTeamScores(UserScoreDTO userScoreDTO) throws IllegalTeamScoreException{
        if (userScoreDTO.getHomeTeamScore()<0||userScoreDTO.getAwayTeamScore()<0) throw new IllegalTeamScoreException("Teams score cannot be less than 0");
    }

    public void validateTimeOfUpsert(UserScoreDTO userScoreDTO, Match match) throws ScoreUpsertTimeException {
        //TODO implement error when new score is created less than 15 mins before match
        Timestamp now = new Timestamp(System.currentTimeMillis());



    }

}
