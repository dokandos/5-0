package com.Matches.infrastructure;

import com.Matches.domain.Match;
import com.Matches.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Match> findByTournamentId(long id);
}
