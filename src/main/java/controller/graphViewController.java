package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.DAO;
import model.Game;
import model.Interval;
import tornadofx.control.DateTimePicker;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static model.DataAnalyzer.findCorrectCount;
import static model.DataAnalyzer.findTotalCount;
import static utility.TypeConverter.integerToInterval;

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

        graphTypeChoiceBox.getItems().add("Interval comparison");

        //remove focus on interactive components on window startup
        Platform.runLater( () -> gridPane.requestFocus() );

    }

    /**
     * Draws a stacked bar chart that shows all the intervals, with the amount of correct and false answers to each
     */
    @FXML
    public void drawIntervalComparisonGraph() {
        graphPane.getChildren().clear();

        Timestamp tsStart;
        Timestamp tsEnd;

        if (startTimePicker.getDateTimeValue() == null) {
            tsStart = Timestamp.valueOf("2020-01-01 00:00:00.0");
        } else {
            tsStart = Timestamp.valueOf(startTimePicker.getDateTimeValue());
        }
        if (endTimePicker.getDateTimeValue() == null) {
            tsEnd = Timestamp.valueOf(java.time.LocalDateTime.now());
        } else {
            tsEnd = Timestamp.valueOf(endTimePicker.getDateTimeValue());
        }
        List<Game> playedGames = DAO.loadUserGameData(tsStart, tsEnd);

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Interval");
        yAxis.setLabel("Count");
        graph = new StackedBarChart<String, Number>(xAxis, yAxis);
        XYChart.Series<String, Number> corrects = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> falses = new XYChart.Series<String, Number>();
        corrects.setName("Correct");
        falses.setName("False");

        List<Interval> intervalList = Arrays.asList(Interval.values());
        System.out.println(intervalList);

        for (int i = 0 ; i < intervalList.size(); i++) {
            Interval interval = integerToInterval(i+1);
            int correctCount = findCorrectCount(playedGames, interval);
            corrects.getData().add(new XYChart.Data<>(interval.name(), correctCount));

            int falseCount = findTotalCount(playedGames, interval) - correctCount;
            falses.getData().add(new XYChart.Data<>(interval.name(), falseCount));
        }

        graph.getData().add(corrects);
        graph.getData().add(falses);

        graphPane.getChildren().add(graph);

    }

}
