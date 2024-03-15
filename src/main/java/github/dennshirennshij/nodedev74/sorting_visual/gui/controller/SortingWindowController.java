package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingDisplay;
import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingWindow;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;

public class SortingWindowController {

    // Root: SortingWindow
    @FXML
    private SortingWindow sortingWindow;

    /* Top Controls */
    @FXML
    private Label checkoutCounter;

    @FXML
    private Label swapCounter;

    @FXML
    private Label algorithmName;

    @FXML
    private Label timer;

    @FXML
    private Button syncButton;

    /* Center Controls */
    @FXML
    private SortingDisplay sortingDisplay;

    /* Bottom Controls */
    @FXML
    private Button startButton;

    @FXML
    private SplitMenuButton speedSetter;

    @FXML
    private Button pauseButton;

    @FXML
    private Button stopButton;

    @FXML
    public void initialize() {
        sortingWindow.setSortingDisplay(sortingDisplay);
    }

    /* Start logic */
    @FXML
    public void StartButtonAction() {

    }

    /* Speed change logic */


    /* Pause logic */
    @FXML
    public void PauseButtonAction() {

    }

    /* Stop logic */
    @FXML
    public void StopButtonAction() {

    }

    /* Sync logic */
    @FXML
    public void SyncButtonAction() {

    }
}
