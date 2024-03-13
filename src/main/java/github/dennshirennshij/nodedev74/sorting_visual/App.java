package github.dennshirennshij.nodedev74.sorting_visual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sorting visual");

        try {
            URL file = getClass().getClassLoader().getResource("fxml/SortingWindow.fxml");
            Parent parent = FXMLLoader.load(file);
            primaryStage.setScene(new Scene(parent));
        } catch(Exception e) {
            System.out.println("Couldnt load fxml");
        }

        primaryStage.show();
    }
}
