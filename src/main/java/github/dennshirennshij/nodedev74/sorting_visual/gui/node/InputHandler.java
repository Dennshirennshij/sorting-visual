package github.dennshirennshij.nodedev74.sorting_visual.gui.node;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;

public class InputHandler extends TabPane {

    public InputHandler() {
        getStylesheets().add("css/input-handler.css");
    }

    public void addNewHandler() {
        InputTab tab = new InputTab();
        getTabs().add(tab);
        setSelectedWindow(getTabs().size() - 1);
    }

    public void setSelectedWindow(int index) {
        getSelectionModel().select(index);
    }

    public boolean hasValidInput(int sortingWindowIndex) {
        return ((InputTab) getTabs().get(sortingWindowIndex)).hasValidInput();
    }

    public int[] getInputArray(int sortingWindowIndex) {
        return ((InputTab) getTabs().get(sortingWindowIndex)).getInputArray();
    }
}
