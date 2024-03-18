package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.event.WindowSelectedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmFinishedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmInitEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.CheckCountChangeEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.SwapCountChangedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.node.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

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
    private TimerControl timer;

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

        /* Register communication events */

        sortingWindow.addEventHandler(SwapCountChangedEvent.EVENT_TYPE, this::updateSwapCounter);
        sortingWindow.addEventHandler(CheckCountChangeEvent.EVENT_TYPE, this::updateCheckCounter);

        sortingWindow.addEventHandler(AlgorithmFinishedEvent.EVENT_TYPE, this::algorithmFinished);
        sortingWindow.addEventHandler(AlgorithmInitEvent.EVENT_TYPE, this::algorithmSpecified);
    }

    /* Start logic */
    @FXML
    public void StartButtonAction() {
        int index = sortingWindow.getIndex();
        InputHandler handler = (InputHandler) sortingWindow.getScene().lookup("#inputHandler");
        if(handler.hasValidInput(index)) {
            sortingWindow.start(handler.getInputArray(index));
            timer.start();
        } else {
            // todo: make visually clear that a valid input is required
        }
    }

    /* Speed change logic */
    @FXML
    public void SpeedChange(ActionEvent evt) {
        MenuItem item = (MenuItem) evt.getTarget();

        sortingWindow.changeCooldown(Float.parseFloat(item.getId()));
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

    /* Counter logic */

    public void updateSwapCounter(SwapCountChangedEvent evt) {
        swapCounter.setText("Swaps: " + evt.getValue());
    }

    public void updateCheckCounter(CheckCountChangeEvent evt) {
        checkoutCounter.setText("Checks: " + evt.getValue());
    }

    public void algorithmSpecified(AlgorithmInitEvent evt) {
        algorithmName.setText(sortingWindow.getAlgorithm().getName());
    }

    public void algorithmFinished(AlgorithmFinishedEvent evt) {
        System.out.println("stopped!!!");
        timer.stop();
    }

    public void selectWindow() {
        int index = sortingWindow.getIndex();

        MainWindow mainWindow = (MainWindow) sortingWindow.getScene().lookup("#Root");
        mainWindow.fireEvent(new WindowSelectedEvent(index));
    }
}
