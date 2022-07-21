package com.ScoreReceiver.domain;

public class ScoreIdGenerator {

    public static String generateId(long matchId, long userId) {
        return matchId + "-" + userId;
    }
}
