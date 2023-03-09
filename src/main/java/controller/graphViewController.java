package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.DAO;
import tornadofx.control.DateTimePicker;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class for controlling GraphView.fxml where the user can see analyzed game data in graphs.
 * Uses custom DateTimePicker included in TordanoFX framework.
 */
public class graphViewController {

    @FXML
    private GridPane gridPane;
    @FXML
    private VBox dateTimePickerVBox;
    private DateTimePicker startTimePicker, endTimePicker;
    @FXML
    private ChoiceBox graphTypeChoiceBox;
    @FXML
    private Pane graphPane;

    /**
     * Creates the DateTimePicker components and adds them into the view.
     * Sets the graph type items the user chooses from.
     */
    public void initialize() {
        startTimePicker = new DateTimePicker();
        endTimePicker = new DateTimePicker();
        startTimePicker.setPromptText("Start date and time");
        endTimePicker.setPromptText("End date and time");
        startTimePicker.setValue(null);
        startTimePicker.setValue(null);

        dateTimePickerVBox.getChildren().add(startTimePicker);
        dateTimePickerVBox.getChildren().add(endTimePicker);

        graphTypeChoiceBox.getItems().add("Note flaw comparison");

        //remove focus on interactive components on window startup
        Platform.runLater( () -> gridPane.requestFocus() );

    }

    public void drawGraph() {
        Timestamp tsStart = Timestamp.valueOf(startTimePicker.getDateTimeValue());
        Timestamp tsEnd = Timestamp.valueOf(endTimePicker.getDateTimeValue());

        DAO.loadUserGameData(tsStart,tsEnd);



    }

}
