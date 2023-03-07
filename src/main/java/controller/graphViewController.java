package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tornadofx.control.DateTimePicker;

/**
 * Class for controlling GraphView.fxml where the user can see analyzed game data in graphs.
 * Uses custom DateTimePicker included in TordanoFX framework.
 */
public class graphViewController {
    @FXML
    VBox dateTimePickerVBox;
    @FXML
    ChoiceBox graphTypeChoiceBox;
    @FXML
    Pane graphPane;

    /**
     * Creates the DateTimePicker components and adds them into the view.
     */
    public void initialize() {
        DateTimePicker dateTimePickerStart = new DateTimePicker();
        DateTimePicker dateTimePickerEnd = new DateTimePicker();

        dateTimePickerStart.setPromptText("Start date and time");
        dateTimePickerEnd.setPromptText("End date and time");

        dateTimePickerVBox.getChildren().add(dateTimePickerStart);
        dateTimePickerVBox.getChildren().add(dateTimePickerEnd);

    }

}
