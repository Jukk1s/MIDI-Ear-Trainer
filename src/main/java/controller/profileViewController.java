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

/**
 * Class for controlling ProfileView.fxml
 *
 * @author Jukka Hallikainen
 */
public class profileViewController {
    @FXML
    private Label clickCountLabel, accuracyLabel;
    @FXML
    private Button biggestFlawButton;

    public static Label static_clickCountLabel, static_accuracyLabel;
    public static Button static_biggestFlawButton;

    /**
     * Sets the initial values that are displayed by the FXML variables.
     * Creates static instances of the FXML variables so that they can be accessed from playViewController.
     */
    public void initialize() {
        setClickCountLabel();
        setAccuracyLabel();
        setBiggestFlawButton();

        static_clickCountLabel = clickCountLabel;
        static_accuracyLabel = accuracyLabel;
        static_biggestFlawButton = biggestFlawButton;
    }

    /**
     * Sets a new value for the FXML label "clickCount".
     */
    public void setClickCountLabel() {
        clickCountLabel.setText(String.valueOf(User.getClickCount()));
    }

    /**
     * Sets a new value for the FXML label "accuracyLabel".
     */
    public void setAccuracyLabel() {
        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String accuracy = df.format(User.getAccuracy());
        accuracyLabel.setText(accuracy + " %");
    }

    /**
     * Sets a new value for the FXML label "biggestFlawButton".
     */
    private void setBiggestFlawButton() {
        if (User.getBiggestFlaw() == 1) {
            biggestFlawButton.setText(String.valueOf(User.getBiggestFlaw()) + "st interval");
        } else if (User.getBiggestFlaw() == 2){
            biggestFlawButton.setText(String.valueOf(User.getBiggestFlaw()) + "nd interval");
        } else if (User.getBiggestFlaw() == 3) {
            biggestFlawButton.setText(String.valueOf(User.getBiggestFlaw()) + "rd interval");
        } else {
            biggestFlawButton.setText(String.valueOf(User.getBiggestFlaw()) + "th interval");
        }
    }

    /**
     * Navigates to PlayView.fxml.
     */
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

    /**
     * Navigates to GraphView.fxml.
     */
    public void goToGraphView(ActionEvent actionEvent) {
    }
}
