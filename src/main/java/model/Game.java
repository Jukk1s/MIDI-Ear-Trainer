package model;

import java.sql.Timestamp;

/**
 * Class that represents a single played game.
 *
 * @author Jukka Hallikainen
 */
public class Game {
    private int userID;
    private int chosenInterval;
    private int correctInterval;
    private Timestamp playedAt;


    public Game() {}

    public Game(int userID, int chosenInterval, int correctInterval, Timestamp timestamp) {
        this.userID = userID;
        this.chosenInterval = chosenInterval;
        this.correctInterval = correctInterval;
        this.playedAt = timestamp;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setSelectedInterval(int chosenInterval) {
        this.chosenInterval = chosenInterval;
    }

    public void setCorrectInterval(int correctInterval) {
        this.correctInterval = correctInterval;
    }

    public void setPlayedAt(Timestamp playedAt) {
        this.playedAt = playedAt;
    }

    public int getUserID() {
        return userID;
    }

    public int getChosenInterval() {
        return chosenInterval;
    }

    public int getCorrectInterval() {
        return correctInterval;
    }

    public Timestamp getPlayedAt() {
        return playedAt;
    }
}
