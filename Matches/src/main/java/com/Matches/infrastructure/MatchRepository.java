package com.Matches.infrastructure;

import com.Matches.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Modifying
    @Query("UPDATE match SET homeTeamScore = ?1, awayTeamScore = ?2, finished = ?3 WHERE id = ?4")
    boolean modifyMatchScore(int homeTeamScore, int awayTeamScore, boolean finished, long matchId);

    List<Match> findByLeagueId(int leagueId);

    List<Match> findByFinished(boolean b);
}
