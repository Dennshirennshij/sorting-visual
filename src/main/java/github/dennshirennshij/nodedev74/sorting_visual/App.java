package github.dennshirennshij.nodedev74.sorting_visual;

import github.dennshirennshij.nodedev74.sorting_visual.gui.view.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sorting visual");

        MainWindow window = new MainWindow();
        Scene scene = new Scene(window);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

    @Override
    public void stop() {

    }
}
