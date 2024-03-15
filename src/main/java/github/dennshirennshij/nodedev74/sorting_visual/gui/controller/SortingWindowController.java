package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingDisplay;
import github.dennshirennshij.nodedev74.sorting_visual.gui.element.SortingWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
        sortingWindow.start(new int[]{1,5,4,6,7,9});
    }

    /* Speed change logic */
    @FXML
    public void SpeedChange(ActionEvent evt) {
        MenuItem item = (MenuItem) evt.getTarget();

        // Change cool down
        sortingWindow.changeCooldown(Float.parseFloat(item.getId()));

        // Update UI value
        speedSetter.setText(item.getId() + "x");
    }


    /* Pause logic */
    @FXML
    public void PauseButtonAction() {
        sortingWindow.togglePause();

        if(sortingWindow.isPaused()) {
            pauseButton.setText("Resume");
        } else if(sortingWindow.isRunning()) {
            pauseButton.setText("Pause");
        }
    }

    /* Stop logic */
    @FXML
    public void StopButtonAction() {
        sortingWindow.setCurrentWindowState(SortingWindow.WindowState.STOPPED); // todo: add closing option later
    }

    /* Sync logic */
    @FXML
    public void SyncButtonAction() {

    }
}
