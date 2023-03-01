package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class profileViewController {

    @FXML
    private Button goToPlayViewButton;
    @FXML
    private Label timesClickedLabel;
    @FXML
    private Label accuracyLabel;

    public void initialize() {
        timesClickedLabel.setText(String.valueOf(User.getTimesClicked()));
        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String accuracy = df.format(User.getAccuracy());
        accuracyLabel.setText(accuracy + " %");
    }

    public void goToPlayView(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../PlayView.fxml"));
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

    public void goToGraphView(ActionEvent actionEvent) {
    }
}
