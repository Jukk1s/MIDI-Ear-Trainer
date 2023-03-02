package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DAO;
import model.TimePeriod;

/**
 * Class that launches the application.
 *
 * @author Jukka Hallikainen
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            DAO.getInstance();
            DAO.loadUserGameData(TimePeriod.All);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../ProfileView.fxml"));
            AnchorPane mainView = loader.load();
            Scene scene = new Scene(mainView);
            stage.setTitle("MIDI Ear Trainer");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}