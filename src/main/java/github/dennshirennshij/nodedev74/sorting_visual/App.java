package github.dennshirennshij.nodedev74.sorting_visual;

import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.example.Bubblesort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    public App() {
        AlgorithmLoader.getInstance();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sorting visual");

        // Create an example SortingWindow
        SortingWindow window = new SortingWindow(Bubblesort.class);
        primaryStage.setScene(new Scene(window));

        primaryStage.show();
    }
}
