package com.ScoreReceiver.service;

import com.ScoreReceiver.DTOs.UserScoreDTO;
import com.ScoreReceiver.domain.Match;
import com.ScoreReceiver.domain.ScoreIdGenerator;
import com.ScoreReceiver.domain.UserScore;
import com.ScoreReceiver.errors.IllegalTeamScoreException;
import com.ScoreReceiver.errors.NoSuchMatchException;
import com.ScoreReceiver.errors.NoSuchScoreException;
import com.ScoreReceiver.errors.ScoreUpsertTimeException;
import com.ScoreReceiver.infrastructure.MatchRepository;
import com.ScoreReceiver.infrastructure.UserScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserScoreService {

    public static final int LIMIT_BEFORE_MATCH_MINUTES = 15;
    private final UserScoreRepository userScoreRepository;
    private final MatchRepository matchRepository;

    public List<UserScore> getScoresForMatch(long matchId) {
        return userScoreRepository.findByMatchId(matchId);
    }

    //TODO replace for createNewScores??
    public void createNewScore(UserScoreDTO userScoreDTO, long userId)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        long matchId = userScoreDTO.getMatchId();
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new NoSuchMatchException("Match id " + matchId  + " not found"));
        validateTeamScores(userScoreDTO);
        validateTimeOfUpsert(userScoreDTO, match);
        userScoreRepository.save(userScoreDTO.mapToUserScore(userId));
    }

    public void createNewScores(List<UserScoreDTO> userScoresDTO, long userId)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException {
        List<UserScore> userScores = new ArrayList<>();
        for (UserScoreDTO userScoreDTO: userScoresDTO) {
            long matchId = userScoreDTO.getMatchId();
            Match match = matchRepository.findById(matchId).orElseThrow(() -> new NoSuchMatchException("Match id " + matchId  + " not found"));
            validateTeamScores(userScoreDTO);
            validateTimeOfUpsert(userScoreDTO, match);
            userScores.add(userScoreDTO.mapToUserScore(userId));
        }
        userScoreRepository.saveAll(userScores);
    }

    public UserScore modifyScore(UserScoreDTO userScoreDTO, String scoreId)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException, NoSuchScoreException {
        long matchId = userScoreDTO.getMatchId();
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new NoSuchMatchException("Match id " + matchId  + " not found"));
        validateTeamScores(userScoreDTO);
        validateTimeOfUpsert(userScoreDTO, match);

        UserScore userScore = userScoreRepository.findById(scoreId).orElseThrow(() -> new NoSuchScoreException("Score id " + scoreId + " not found"));
        userScore.modifyScore(userScoreDTO);
        return userScoreRepository.save(userScore);
    }

    public List<UserScore> modifyScores(List<UserScoreDTO> userScoresDTO, long userId)
            throws NoSuchMatchException, IllegalTeamScoreException, ScoreUpsertTimeException, NoSuchScoreException {
        List<UserScore> userScores = new ArrayList<>();
        for (UserScoreDTO userScoreDTO: userScoresDTO) {
            long matchId = userScoreDTO.getMatchId();
            Match match = matchRepository.findById(matchId).orElseThrow(() -> new NoSuchMatchException("Match id " + matchId + " not found"));
            validateTeamScores(userScoreDTO);
            validateTimeOfUpsert(userScoreDTO, match);

            String scoreId = ScoreIdGenerator.generateId(matchId, userId);
            UserScore userScore = userScoreRepository.findById(scoreId).orElseThrow(() -> new NoSuchScoreException("Score id " + scoreId + " not found"));
            userScore.modifyScore(userScoreDTO);
        }
        return userScoreRepository.saveAll(userScores);
    }

    public void validateTeamScores(UserScoreDTO userScoreDTO) throws IllegalTeamScoreException{
        if (userScoreDTO.getHomeTeamScore()<0||userScoreDTO.getAwayTeamScore()<0) throw new IllegalTeamScoreException("Teams score cannot be less than 0");
    }

    public void validateTimeOfUpsert(UserScoreDTO userScoreDTO, Match match) throws ScoreUpsertTimeException {
        Timestamp upsert = userScoreDTO.getTimestamp();
        Timestamp limitUpsertTimeStamp = new Timestamp(match.getTimestamp().getTime() - TimeUnit.MINUTES.toMillis(LIMIT_BEFORE_MATCH_MINUTES));

        if (limitUpsertTimeStamp.compareTo(upsert)<0) throw new ScoreUpsertTimeException("Cannot create or modify match due to time restrictions");
    }
}
