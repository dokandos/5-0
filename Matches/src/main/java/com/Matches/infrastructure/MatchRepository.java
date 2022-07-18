package com.Matches.infrastructure;

import com.Matches.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Modifying
    @Query(value = "UPDATE Match m SET m.homeTeamScore = ?1, m.awayTeamScore = ?2, m.finished = ?3 WHERE m.id = ?4")
    boolean modifyMatchScore(int homeTeamScore, int awayTeamScore, boolean finished, long matchId);

    List<Match> findById(int tournamentId);

    List<Match> findByFinished(boolean b);
}
