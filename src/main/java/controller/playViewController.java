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
import model.NotePlayer;
import model.User;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class playViewController extends profileViewController {
    private NotePlayer notePlayer;
    private int rand1, rand2;
    private int correctInterval, selectedInterval;
    private boolean buttonPressed, notesPlaying, answerChecked;
    @FXML
    private Button playButton;
    @FXML
    private ChoiceBox intervalChoiceBox;
    @FXML
    private Label wrongAnswerLabel;

    public void initialize() {
        notePlayer = new NotePlayer();
        answerChecked = true;
        setChoiceBoxItems();
        intervalChoiceBox.setDisable(true);
    }

    public void setChoiceBoxItems() {
        for (int i = 1; i < 13; i++) {
            intervalChoiceBox.getItems().add(i);
        }
        intervalChoiceBox.setOnAction(event -> {
            if (!answerChecked) {
                selectedInterval = (int) intervalChoiceBox.getSelectionModel().getSelectedItem();
                checkIfAnswerCorrect();
                intervalChoiceBox.getSelectionModel().clearSelection();
            }
        });
    }

    public void generateNotes() {
        Random rand = new Random();
        rand1 = rand.nextInt(73 - 60) + 60;
        do {
            rand2 = rand.nextInt(73 - 60) + 60;
        } while (rand2 == rand1);

        calculateInterval(rand1, rand2);
    }

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

    public void calculateInterval(int note1, int note2) {
        if (note1 > note2) {
            correctInterval = note1 - note2;
        } else {
            correctInterval = note2 - note1;
        }
    }

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
        static_clickCountLabel.setText(String.valueOf(User.getClickCount()));

    }

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