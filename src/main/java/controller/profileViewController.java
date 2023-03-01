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

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class profileViewController {

    @FXML
    private Button goToPlayViewButton;
    @FXML
    private Label clickCountLabel;
    @FXML
    private Label accuracyLabel;

    public static Label static_clickCountLabel;

    public static Label static_accuracyLabel;

    public void initialize() {
        setClickCountLabel();
        setAccuracyLabel();

        static_clickCountLabel = clickCountLabel;
        static_accuracyLabel = accuracyLabel;
    }

    public void setClickCountLabel() {
        clickCountLabel.setText(String.valueOf(User.getClickCount()));
        System.out.println(String.valueOf(User.getClickCount()));
    }

    public void setAccuracyLabel() {
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
