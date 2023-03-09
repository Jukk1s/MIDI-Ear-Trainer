package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DAO;
import model.Interval;
import model.NotePlayer;
import model.User;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import static utility.TypeConverter.integerToInterval;
import static utility.TypeConverter.intervalToInteger;

/**
 * Class for controlling PlayView.fxml
 */
public class playViewController extends profileViewController {
    private NotePlayer notePlayer;
    private int rand1, rand2;
    private Interval correctInterval, selectedInterval;
    private boolean buttonPressed, notesPlaying, answerChecked;
    @FXML
    private Button playButton;
    @FXML
    private ChoiceBox intervalChoiceBox;
    @FXML
    private Label wrongAnswerLabel;

    /**
     * Sets initial values and states for the class members.
     */
    public void initialize() {
        notePlayer = new NotePlayer();
        answerChecked = true;
        setChoiceBoxItems();
        intervalChoiceBox.setDisable(true);
    }

    /**
     * Adds (musical) interval values between 1 and 12 into ChoiceBox.
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
     * Generates two random notes in range of an octave.
     */
    public void generateNotes() {
        Random rand = new Random();
        rand1 = rand.nextInt(73 - 60) + 60;
        do {
            rand2 = rand.nextInt(73 - 60) + 60;
        } while (rand2 == rand1);

        calculateInterval(rand1, rand2);
    }

    /**
     * Checks if circumstances are OK for generating and playing notes.
     * Calls for methods to generate and play the generated notes.
     */
    public void initPlayNotes() {
        if (notesPlaying) {
            return;
        }
        if (answerChecked) {
            generateNotes();
        }
        intervalChoiceBox.setDisable(false);
        wrongAnswerLabel.setText("");
        buttonPressed = true;
        answerChecked = false;

        playNotes();
    }

    /**
     * Calls for NotePlayer to play two notes in new thread so that the UI doesn't freeze.
     */
    private void playNotes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                notesPlaying = true;
                playButton.setDisable(true);
                notePlayer.playNote(rand1);
                notePlayer.playNote(rand2);
                notesPlaying = false;
                playButton.setDisable(false);
            }
        }).start();
    }

    /**
     * Calculates the interval in semitones between provided notes.
     * @param note1 pitch of the note translated into Integer value
     * @param note2 pitch of the note translated into Integer value
     */
    public void calculateInterval(int note1, int note2) {
        if (note1 > note2) {
            correctInterval = integerToInterval(note1 - note2);
        } else {
            correctInterval = integerToInterval(note2 - note1);
        }
    }

    /**
     * Checks if user has chosen the correct answer.
     * Updates Labels, User variables and DAO accordingly.
     */
    private void checkIfAnswerCorrect() {
        if (!answerChecked) {
            if (buttonPressed && selectedInterval == correctInterval) {
                wrongAnswerLabel.setText("Correct!");
                User.increaseCorrectCount();
            } else {
                wrongAnswerLabel.setText("Wrong. Correct interval was " + correctInterval);
            }
        }
        answerChecked = true;
        intervalChoiceBox.setDisable(true);

        DAO.getInstance().saveGame(selectedInterval, correctInterval);
        User.increaseClickCount();
        User.setAccuracy();

        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String accuracy = df.format(User.getAccuracy());
        static_accuracyLabel.setText(accuracy + " %");
        static_clickCountLabel.setText(String.valueOf(User.getGameCount()));

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