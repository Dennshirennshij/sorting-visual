package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowIndexUpdateEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowSelectedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmFinishedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmInitEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.deliver.CheckCountChangeEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.deliver.SwapCountChangedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowStateChangedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TimerControl timer;

    @FXML
    private Button closeButton;

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

        sortingWindow.addEventHandler(WindowStateChangedEvent.EVENT_TYPE, this::windowStateChanged);
        sortingWindow.addEventHandler(WindowIndexUpdateEvent.EVENT_TYPE, this::windowIndexUpdate);
    }

    public void windowStateChanged(WindowStateChangedEvent evt) {
        if(evt.getNewState() == SortingWindow.WindowState.RUNNING) {
            pauseButton.setText("Pause");
            startButton.setDisable(true);
            closeButton.setDisable(true);
        }

        if(evt.getNewState() == SortingWindow.WindowState.PAUSED) {
            pauseButton.setText("Resume");
        }

        if(evt.getNewState() == SortingWindow.WindowState.STOPPED) {
            closeButton.setDisable(false);
            pauseButton.setDisable(true);
        }

        if(evt.getNewState() == SortingWindow.WindowState.FINISHED) {
            closeButton.setDisable(false);
        }
    }

    public void windowIndexUpdate(WindowIndexUpdateEvent evt) {
        int removedIndex = evt.getRemovedIndex();
        int currentIndex = sortingWindow.getIndex();

        if(currentIndex > removedIndex) {
            sortingWindow.setIndex(--currentIndex);
        } else {
            sortingWindow.setIndex(++currentIndex);
        }
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
        float factor = Float.parseFloat(item.getId());

        sortingWindow.changeCooldown(factor);
        speedSetter.setText(item.getId() + "x");
    }


    /* Pause logic */
    @FXML
    public void PauseButtonAction() {
        sortingWindow.togglePause();
        timer.togglePause();
    }

    /* Stop logic */
    @FXML
    public void StopButtonAction() {
        sortingWindow.setCurrentWindowState(SortingWindow.WindowState.STOPPED);
        timer.stop();
    }

    /* Sync logic */
    @FXML
    public void CloseButtonAction() {
        timer.stop();
        sortingWindow.killTask();
        sortingWindow.destroy();
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
        timer.stop();
    }

    public void selectWindow() {
        int index = sortingWindow.getIndex();

        MainWindow mainWindow = (MainWindow) sortingWindow.getScene().lookup("#Root");
        mainWindow.fireEvent(new WindowSelectedEvent(index));
    }
}
