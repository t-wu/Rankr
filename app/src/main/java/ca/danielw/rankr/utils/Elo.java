package ca.danielw.rankr.utils;

import android.util.Log;

import ca.danielw.rankr.models.RankingModel;

public class Elo {
    private static Double advantage = 400.0;

    public static void calculateElo(RankingModel userA, RankingModel userB, boolean result){
        Double scoreA = result ? 1.0 : 0.0;
        Double scoreB = result ? 0.0 : 1.0;

        int ratingA = userA.getElo();
        int ratingB = userB.getElo();

        Double Ea = expectedRating(ratingA, ratingB);
        Double Eb = 1.0 - Ea;

        int newRatingA = ratingA + (int) Math.round(userA.getkFactor() * (scoreA - Ea));
        int newRatingB = ratingB + (int) Math.round(userB.getkFactor() * (scoreB - Eb));

        Log.e("Rating A", String.valueOf(newRatingA));
        Log.e("Rating B", String.valueOf(newRatingB));

        userA.setElo(newRatingA);
        userB.setElo(newRatingB);
    }

    private static Double expectedRating(int ratingA, int ratingB){
        Double denom = 1.0 + Math.pow(10.0, (ratingB - ratingA) / advantage);
        return 1.0 / denom;
    }

}
