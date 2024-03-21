package github.dennshirennshij.nodedev74.sorting_visual.gui.view;

import javafx.scene.control.TabPane;

public class InputHandler extends TabPane {

    public InputHandler() {
        getStylesheets().add("css/input-handler.css");
    }

    public void addHandler() {
        InputTab tab = new InputTab();
        getTabs().add(tab);
        setSelectedWindow(getTabs().size() - 1);
    }

    public void removeHandler(int index) {
        getTabs().remove(index);
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
