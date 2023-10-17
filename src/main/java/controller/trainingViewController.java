package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import static controller.profileViewController.static_accuracyLabel;
import static controller.profileViewController.static_playCountLabel;
import static utility.EnumConverter.*;

/**
 * Class for controlling TrainingView.fxml
 * Training view is used for training specific intervals.
 */
public class trainingViewController {

    private int noteA1, noteA2, noteB1, noteB2;
    private boolean buttonPressed, notesPlaying, answerChecked, intervalChoiceChecked;
    private Random rand;
    private static GameType gameType;
    private NotePlayer notePlayer;
    private DataAnalyzer dataAnalyzer;
    private Interval intervalToTrain, selectedInterval, correctInterval;
    @FXML
    private Label questionLabel, feedbackLabel;
    @FXML
    private ChoiceBox<Interval> intervalToTrainChoiceBox;
    @FXML
    private ChoiceBox<String> answerChoiceBox;
    @FXML
    private Button playButton1, playButton2;

    /**
     * Sets the initial values and states for the class members.
     */
    public void initialize() {
        gameType = GameType.TRAINING;
        rand = new Random();
        intervalChoiceChecked = false;
        notePlayer = new NotePlayer();
        setIntervalToTrainChoiceBoxItems();
        setAnswerChoiceBoxItems();
        answerChoiceBox.setDisable(true);
        playButton1.setDisable(true);
        playButton2.setDisable(true);
    }

    /**
     * Adds musical interval values between 1 and 12 into ChoiceBox.
     */
    private void setIntervalToTrainChoiceBoxItems() {
        for (int i = 0; i < 12; i++) {
            Interval interval = integerToInterval(i+1);
            intervalToTrainChoiceBox.getItems().add(interval);
        }
        intervalToTrainChoiceBox.setOnAction(event -> {
            intervalToTrain = intervalToTrainChoiceBox.getSelectionModel().getSelectedItem();
            questionLabel.setText("Which one is " + intervalToString(intervalToTrain) + "?");
            correctInterval = intervalToTrain;
            playButton1.setDisable(false);
            playButton2.setDisable(false);
            generateNotes(intervalToTrain);
        });
    }

    /**
     * Adds the options A and B for the user to choose the right answer from.
     */
    private void setAnswerChoiceBoxItems() {
        answerChoiceBox.getItems().add("A");
        answerChoiceBox.getItems().add("B");
        answerChoiceBox.setOnAction(event -> {
            String answer = answerChoiceBox.getSelectionModel().getSelectedItem();
            if (answer == "A") {
                selectedInterval = integerToInterval(noteA2);
            } else {
                selectedInterval = integerToInterval(noteB2);
            }
            checkIfAnswerCorrect();
        });
    }

    /**
     * Generates notes for both buttons to play, and randomizes which one plays the correct interval.
     * The incorrect interval is chosen based on user game data: the most commonly mistaken interval.
     */
    public void generateNotes(Interval interval) {
        if (intervalToTrain != null) {
            List<Game> gameList = DAO.loadUserGameDataByInterval(TimePeriod.ALL, interval);
            Interval mostCommonMistake = dataAnalyzer.findMostCommonMistake(gameList);

            int selectedIntervalInt = intervalToInteger(intervalToTrain);
            int baseNote = 0;

            int oneOrTwo = rand.nextInt(2) + 1;
            if (oneOrTwo == 1) {
                noteA1 = baseNote;
                noteA2 = selectedIntervalInt;
                noteB1 = baseNote;
                noteB2 = intervalToInteger(mostCommonMistake);
            } else {
                noteA1 = baseNote;
                noteA2 = intervalToInteger(mostCommonMistake);
                noteB1 = baseNote;
                noteB2 = selectedIntervalInt;
            }
        }
    }

    /**
     * Checks whether the answer is correct and displays feedback.
     * Updates Labels, User variables and DAO accordingly.
     */
    public void checkIfAnswerCorrect() {
        if (selectedInterval == correctInterval) {
            feedbackLabel.setText("Correct!");
        } else {
            feedbackLabel.setText("Wrong. Correct interval was " + intervalToString(correctInterval));
        }
        answerChoiceBox.setDisable(true);

        DAO.getInstance().saveGame(gameType, selectedInterval, correctInterval);
        User.increaseClickCount();
        User.setAccuracy();

        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String accuracy = df.format(User.getAccuracy());
        static_accuracyLabel.setText(accuracy + " %");
        static_playCountLabel.setText(String.valueOf(User.getGameCount()));
    }

    /**
     * Calls for methods to play the first set of notes.
     * Notes are played in new thread so that the UI doesn't freeze.
     */
    @FXML
    public void playNotesOne() {
        if (notesPlaying) {
            return;
        }
        if (answerChecked) {
            generateNotes(intervalToTrain);
        }
        answerChoiceBox.setDisable(false);
        feedbackLabel.setText("");
        buttonPressed = true;
        answerChecked = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                notesPlaying = true;
                playButton1.setDisable(true);
                notePlayer.playNote(noteA1);
                notePlayer.playNote(noteA2);
                notesPlaying = false;
                playButton1.setDisable(false);
            }
        }).start();
    }

    /**
     * Calls for methods to play the second set of notes.
     * Notes are played in new thread so that the UI doesn't freeze.
     */
    @FXML
    public void playNotesTwo() {
        if (notesPlaying) {
            return;
        }
        if (answerChecked) {
            generateNotes(intervalToTrain);
        }
        answerChoiceBox.setDisable(false);
        feedbackLabel.setText("");
        buttonPressed = true;
        answerChecked = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                notesPlaying = true;
                playButton2.setDisable(true);
                notePlayer.playNote(noteB1);
                notePlayer.playNote(noteB2);
                notesPlaying = false;
                playButton2.setDisable(false);
            }
        }).start();
    }

}