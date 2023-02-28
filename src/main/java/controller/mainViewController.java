package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.NotePlayer;

import java.util.Random;

public class mainViewController {
    private NotePlayer notePlayer;
    private int rand1, rand2;
    private int correctInterval, chosenInterval;
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
    }

    public void setChoiceBoxItems() {
        for (int i = 1; i < 13 ; i++) {
            intervalChoiceBox.getItems().add(i);
        }
        intervalChoiceBox.setOnAction(event -> {
            if (!answerChecked) {
                chosenInterval = (int) intervalChoiceBox.getSelectionModel().getSelectedItem();
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
            if (buttonPressed && chosenInterval == correctInterval) {
                wrongAnswerLabel.setText("Oikein");
            } else {
                wrongAnswerLabel.setText("Väärin. Oikea intervalli oli " + correctInterval);
            }
        }
        answerChecked = true;
        intervalChoiceBox.setDisable(true);
        //playButton.setDisable(false);

        //playNotes();
    }

}