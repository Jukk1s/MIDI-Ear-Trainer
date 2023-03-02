package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DAO;
import model.TimePeriod;
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
    private Label clickCountLabel, accuracyLabel, timePeriodLabel;
    @FXML
    private Slider timePeriodSlider;
    @FXML
    private Button biggestFlawButton;

    public static Label static_clickCountLabel, static_accuracyLabel;
    public static Button static_biggestFlawButton;

    /**
     * Sets the initial values that are displayed by the FXML variables.
     * Creates static instances of the FXML variables so that they can be accessed from playViewController.
     */
    public void initialize() {
        setTimePeriod();

        static_clickCountLabel = clickCountLabel;
        static_accuracyLabel = accuracyLabel;
        static_biggestFlawButton = biggestFlawButton;
    }

    /**
     * Checks the current value of the Slider FXML object.
     * Sets time period based on the value.
     * Loads game data so that only the games played during the time period are analyzed and displayed in labels.
     * Is called when the user clicks or releases a mouse drag on the Slider.
     */
    @FXML
    private void setTimePeriod() {
        TimePeriod period = null;
        int sliderValue = (int) Math.round(timePeriodSlider.getValue());

        switch (sliderValue) {
            case 1 -> period = TimePeriod.All;
            case 2 -> period = TimePeriod.Hour;
            case 3 -> period = TimePeriod.Day;
            case 4 -> period = TimePeriod.Week;
            case 5 -> period = TimePeriod.Month;
            case 6 -> period = TimePeriod.Year;
            default -> throw new IllegalStateException("Unexpected value: " + sliderValue);
        }
        timePeriodLabel.setText(period.name());
        DAO.loadUserGameData(period);
        setClickCountLabel();
        setAccuracyLabel();
        setBiggestFlawButton();
    }

    /**
     * Sets value for the FXML label "clickCount".
     */
    public void setClickCountLabel() {
        clickCountLabel.setText(String.valueOf(User.getClickCount()));
    }

    /**
     * Sets value for the FXML label "accuracyLabel".
     */
    public void setAccuracyLabel() {
        if (Double.isNaN(User.getAccuracy())) {
            accuracyLabel.setText("-");
            return;
        }
        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String accuracy = df.format(User.getAccuracy());
        accuracyLabel.setText(accuracy + " %");
    }

    /**
     * Sets value for the FXML label "biggestFlawButton".
     */
    private void setBiggestFlawButton() {
        biggestFlawButton.setDisable(false);

        if (User.getBiggestFlaw() == 0) {
            biggestFlawButton.setDisable(true);
            biggestFlawButton.setText("-");
        } else if (User.getBiggestFlaw() == 1) {
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
    @FXML
    public void goToPlayView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../PlayView.fxml"));
            AnchorPane playView = loader.load();
            Scene scene = new Scene(playView);
            Stage stage = new Stage();
            stage.setTitle("MIDI Ear Trainer");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates to GraphView.fxml.
     */
    @FXML
    public void goToGraphView(ActionEvent actionEvent) {
        //TODO
    }
}
