package github.dennshirennshij.nodedev74.sorting_visual;

import github.dennshirennshij.nodedev74.sorting_visual.gui.node.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public App() {

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sorting visual");

        // Create an example SortingWindow
        MainWindow window = new MainWindow();
        primaryStage.setScene(new Scene(window));

        primaryStage.show();
    }

    @Override
    public void stop() {

    }
}
