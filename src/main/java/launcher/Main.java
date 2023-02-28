package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DAO;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            DAO.getInstance();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../ProfileView.fxml"));
            AnchorPane mainView = loader.load();
            Scene scene = new Scene(mainView);
            stage.setTitle("MIDI Ear Trainer - Profile");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}