package com.ahsan.azquizapp;

public class Score {
    private int gainScore;
    private int totalScore;

    public Score(int gainScore, int totalScore) {
        this.gainScore = gainScore;
        this.totalScore = totalScore;
    }

    public int getGainScore() {
        return gainScore;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
