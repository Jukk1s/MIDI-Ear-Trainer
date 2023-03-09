package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.DAO;
import model.Interval;
import tornadofx.control.DateTimePicker;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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

    private XYChart<String, Number> graph;
    private Axis xAxis, yAxis;

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

    /**
     * Draws a stacked bar chart that shows all the intervals, and the amount of correct and false answers
     */
    @FXML
    public void drawNoteFlawComparisonGraph() {

        Timestamp tsStart = Timestamp.valueOf(startTimePicker.getDateTimeValue());
        Timestamp tsEnd = Timestamp.valueOf(endTimePicker.getDateTimeValue());

        DAO.loadUserGameData(tsStart,tsEnd);

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Interval");
        yAxis.setLabel("Count");

        List<Interval> intervalList = Arrays.asList(Interval.values());
        System.out.println(intervalList.toString());

        graph = new StackedBarChart<String, Number>(xAxis, yAxis);

        XYChart.Series<String, Number> correctSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> falseSeries = new XYChart.Series<String, Number>();
        correctSeries.setName("Correct");
        falseSeries.setName("False");

        //correctSeries.

        //correctSeries.setData();


    }

}
