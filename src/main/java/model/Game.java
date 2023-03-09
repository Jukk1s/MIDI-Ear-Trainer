package model;

import java.sql.Timestamp;

/**
 * Class that represents a single played game.
 */
public class Game {
    private int userID;
    private Interval chosenInterval;
    private Interval correctInterval;
    private Timestamp playedAt;


    public Game() {}

    public Game(int userID, Interval chosenInterval, Interval correctInterval, Timestamp timestamp) {
        this.userID = userID;
        this.chosenInterval = chosenInterval;
        this.correctInterval = correctInterval;
        this.playedAt = timestamp;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setSelectedInterval(Interval chosenInterval) {
        this.chosenInterval = chosenInterval;
    }

    public void setCorrectInterval(Interval correctInterval) {
        this.correctInterval = correctInterval;
    }

    public void setPlayedAt(Timestamp playedAt) {
        this.playedAt = playedAt;
    }

    public int getUserID() {
        return userID;
    }

    public Interval getChosenInterval() {
        return chosenInterval;
    }

    public Interval getCorrectInterval() {
        return correctInterval;
    }

    public Timestamp getPlayedAt() {
        return playedAt;
    }
}
