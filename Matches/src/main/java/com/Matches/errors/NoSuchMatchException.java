package com.Matches.errors;

public class NoSuchMatchException extends Exception{

    public NoSuchMatchException(long matchId) {
        super("Match id: " + matchId + " not found in database.");
    }
}
