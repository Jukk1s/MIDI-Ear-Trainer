package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static utility.EnumConverter.*;
import static model.Calculator.calculateInterval;

/**
 * Class for controlling PlayView.fxml
 */
public class playViewController extends profileViewController {
    private NotePlayer notePlayer;
    private NoteGenerator noteGenerator;
    private Note note1, note2;
    private Interval correctInterval, selectedInterval;
    private boolean buttonPressed, notesPlaying, answerChecked;
    private GameType gameType;
    @FXML
    private Button playButton;
    @FXML
    private ChoiceBox intervalChoiceBox;
    @FXML
    private Label feedbackLabel;

    /**
     * Sets the initial values and states for the class members.
     */
    public void initialize() {
        gameType = GameType.GAME;
        notePlayer = new NotePlayer();
        noteGenerator = new NoteGenerator();
        answerChecked = true;
        setChoiceBoxItems();
        intervalChoiceBox.setDisable(true);
    }

    /**
     * Adds musical interval values between 1 and 12 into ChoiceBox.
     */
    public void setChoiceBoxItems() {
        for (int i = 0; i < 12; i++) {
            Interval interval = integerToInterval(i+1);
            intervalChoiceBox.getItems().add(interval);
        }
        intervalChoiceBox.setOnAction(event -> {
            if (!answerChecked) {
                selectedInterval = (Interval) intervalChoiceBox.getSelectionModel().getSelectedItem();
                checkIfAnswerCorrect();
                intervalChoiceBox.getSelectionModel().clearSelection();
            }
        });
    }

    /**
     * Generates two random notes in a range of octave.
     */
    public void generateNotes() {
        Note[] notes = noteGenerator.generateTwoNotesWithRandomInterval();
        note1 = notes[0];
        note2 = notes[1];
    }

    /**
     * Checks if circumstances are OK for generating and playing notes.
     * Calls for methods to generate and play the generated notes.
     * Calls for NotePlayer to play two notes in new thread so that the UI doesn't freeze.
     */
    @FXML
    private void playNotes() {
        if (notesPlaying) {
            return;
        }
        if (answerChecked) {
            generateNotes();
            correctInterval = calculateInterval(note1, note2);
        }
        intervalChoiceBox.setDisable(false);
        feedbackLabel.setText("");
        buttonPressed = true;
        answerChecked = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                notesPlaying = true;
                playButton.setDisable(true);
                notePlayer.playNote(note1);
                notePlayer.playNote(note2);
                notesPlaying = false;
                playButton.setDisable(false);
            }
        }).start();
    }

    /**
     * Checks if user has chosen the correct answer.
     * Updates Labels, User variables and DAO accordingly.
     */
    private void checkIfAnswerCorrect() {
        if (!answerChecked) {
            if (buttonPressed && selectedInterval == correctInterval) {
                feedbackLabel.setText("Correct!");
                User.increaseCorrectCount();
            } else {
                feedbackLabel.setText("Wrong. Correct interval was " + intervalToString(correctInterval));
            }
        }
        answerChecked = true;
        intervalChoiceBox.setDisable(true);

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
     * Navigates to ProfileView.fxml.
     */
    @FXML
    private void goToProfile() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../ProfileView.fxml"));
            AnchorPane playView = loader.load();
            Scene scene = new Scene(playView);
            Stage stage = new Stage();
            stage.setTitle("MIDI Ear Trainer");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}