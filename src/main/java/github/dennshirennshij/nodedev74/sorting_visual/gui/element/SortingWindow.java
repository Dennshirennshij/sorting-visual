package github.dennshirennshij.nodedev74.sorting_visual.gui.element;

import javafx.scene.layout.BorderPane;

public class SortingWindow extends BorderPane {

    public enum WindowState
    {
        PAUSED,
        RUNNING,
        STOPPED
    }

    private WindowState currentWindowState;

    public WindowState getCurrentWindowState() {
        return currentWindowState;
    }

    public void setCurrentWindowState(WindowState newState) {
        currentWindowState = newState;
    }
}
