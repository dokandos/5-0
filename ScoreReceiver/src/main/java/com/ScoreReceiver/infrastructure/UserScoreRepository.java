package com.ScoreReceiver.infrastructure;

import com.ScoreReceiver.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    List<UserScore> findByMatchId(long matchId);
}
