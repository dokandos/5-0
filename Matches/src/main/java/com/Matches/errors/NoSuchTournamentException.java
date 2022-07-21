package com.Matches.errors;

public class NoSuchTournamentException extends Exception{
    public NoSuchTournamentException(long tournamentId) {
        super("No tournament with ID: " + tournamentId);
    }
}
