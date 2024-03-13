package github.dennshirennshij.nodedev74.sorting_visual.gui.element;

import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.net.URL;

public class SortingWindow extends BorderPane {

    public enum WindowState
    {
        PAUSED,
        RUNNING,
        STOPPED
    }

    private WindowState currentWindowState;

    /* Constructors */

    public SortingWindow() {
        try {
            URL file = getClass().getClassLoader().getResource("fxml/SortingWindow.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml");
        }
    }
    public SortingWindow(Algorithm algorithm) {
        this();

        // todo: impl initial logic
    }

    public WindowState getCurrentWindowState() {
        return currentWindowState;
    }

    public void setCurrentWindowState(WindowState newState) {
        currentWindowState = newState;
    }
}
