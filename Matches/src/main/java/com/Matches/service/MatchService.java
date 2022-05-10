package com.Matches.service;

import com.Matches.domain.Match;
import com.Matches.domain.Tournament;
import com.Matches.errors.NoSuchMatchException;
import com.Matches.infrastructure.MatchRepository;
import com.Matches.infrastructure.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;

    /*
    * Methods for matchRepository
    * */
    public void createNewMatch(Match match) {
        matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Match> getMatchesForLeagueId(int leagueId) {
        return matchRepository.findByLeagueId(leagueId);
    }

    public List<Match> getUnfinishedMatches() {
        return matchRepository.findByFinished(true);
    }

    public Match getMatchById(long id) throws NoSuchMatchException {
        return matchRepository.findById(id).orElseThrow(() -> new NoSuchMatchException(id));
    }

//    Only modify when the match is over (PATCH?)
    public void modifyScores(int homeTeamScore, int awayTeamScore, long matchId) throws NoSuchMatchException {
        boolean modified = matchRepository.modifyMatchScore(homeTeamScore, awayTeamScore, true, matchId);
        if (!modified) throw new NoSuchMatchException(matchId);
    }

    /*
     * Methods for touramentRepository
     * */
    public Optional<Tournament> findTournamentById(long id) {
        return tournamentRepository.findById(id);
    }

    public void createNewTournament(Tournament tournament){
        if (findTournamentById(tournament.getTournamentId()).isPresent()) return;
        else tournamentRepository.save(tournament);
    }

    public List<Match> getMatchesByTournamentId(long id) {
        return tournamentRepository.findByTournamentId(id);
    }
}
