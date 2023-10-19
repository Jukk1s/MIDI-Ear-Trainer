package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DAO;
import model.TimePeriod;
import model.User;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static utility.EnumConverter.intervalToString;

/**
 * Class for controlling ProfileView.fxml
 */
public class profileViewController {
    @FXML
    private Label playCountLabel, accuracyLabel, timePeriodLabel;
    @FXML
    private Slider timePeriodSlider;
    @FXML
    private Label biggestFlawLabel;

    public static Label static_playCountLabel, static_accuracyLabel;
    public static Label static_biggestFlawLabel;

    /**
     * Sets the initial values that are displayed by the FXML variables.
     * Creates static instances of the FXML variables so that they can be accessed from playViewController and trainingViewController.
     */
    public void initialize() {
        setTimePeriod();

        static_playCountLabel = playCountLabel;
        static_accuracyLabel = accuracyLabel;
        static_biggestFlawLabel = biggestFlawLabel;
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
            case 1 -> period = TimePeriod.ALL;
            case 2 -> period = TimePeriod.YEAR;
            case 3 -> period = TimePeriod.MONTH;
            case 4 -> period = TimePeriod.WEEK;
            case 5 -> period = TimePeriod.DAY;
            case 6 -> period = TimePeriod.HOUR;
            default -> throw new IllegalStateException("Unexpected value: " + sliderValue);
        }
        timePeriodLabel.setText(period.name());
        DAO.loadUserGameData(period);
        setClickCountLabel();
        setAccuracyLabel();
        setBiggestFlawLabel();
    }

    /**
     * Sets value for the FXML label "clickCount".
     */
    public void setClickCountLabel() {
        playCountLabel.setText(String.valueOf(User.getGameCount()));
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
     * Sets value for the FXML label "biggestFlawLabel".
     */
    private void setBiggestFlawLabel() {
        biggestFlawLabel.setDisable(false);

        if (User.getBiggestFlaw() == null) {
            biggestFlawLabel.setDisable(true);
            biggestFlawLabel.setText("-");
        } else {
            biggestFlawLabel.setText(User.getBiggestFlaw().toString());
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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../GraphView.fxml"));
            GridPane graphView = loader.load();
            Scene scene = new Scene(graphView);
            Stage stage = new Stage();
            stage.setTitle("MIDI Ear Trainer - Graphs");
            stage.setScene(scene);
            stage.getScene().getStylesheets().add("styles.css");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToTrainingView(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../TrainingView.fxml"));
            AnchorPane trainingView = loader.load();
            Scene scene = new Scene(trainingView);
            Stage stage = new Stage();
            stage.setTitle("MIDI Ear Trainer - Training");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
