package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.event.deliver.CreationAppliedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowCreatedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowRemovedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowSelectedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.layout.WindowLayout;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.CreationDialog;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.InputHandler;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.MainWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainWindowController {

    private Dialog<Boolean> currentDialog;

    @FXML
    public MainWindow Root;

    @FXML
    public WindowLayout tilePane;

    @FXML
    public Button addWindowButton;

    @FXML
    public Label windowCounter;

    @FXML
    public InputHandler inputHandler;

    @FXML
    public void initialize() {
        Root.addEventHandler(WindowCreatedEvent.EVENT_TYPE, this::creationOk);
        Root.addEventHandler(WindowSelectedEvent.EVENT_TYPE, this::newWindowSelected);
        Root.addEventHandler(WindowRemovedEvent.EVENT_TYPE, this::windowRemoved);
    }

    @FXML
    public void addNewTile() {
        currentDialog = new Dialog<>();
        currentDialog.setTitle("Create Sorting Window");
        currentDialog.setDialogPane(new CreationDialog());

        Stage stage = (Stage) currentDialog.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        currentDialog.getDialogPane().addEventHandler(CreationAppliedEvent.EVENT_TYPE, this::gotTileData);

        currentDialog.show();
    }

    public void creationOk(WindowCreatedEvent evt) {
        if(tilePane.isFull()) {
            addWindowButton.setDisable(true);
        }
        windowCounter.setText(tilePane.countWindows() + "/" + tilePane.MAX_LAYERS * tilePane.MAX_LAYER_NODES);
        inputHandler.addHandler();

        currentDialog.setResult(Boolean.TRUE);
        currentDialog.close();
    }

    public void gotTileData(CreationAppliedEvent evt) {
        AlgorithmLoader loader = AlgorithmLoader.getInstance();
        Class<?extends Algorithm> algo = loader.getAlgorithmClassByName(evt.getAlgorithm());

        Root.createSortingWindowTile(algo);
    }

    public void windowRemoved(WindowRemovedEvent evt) {
        windowCounter.setText(tilePane.countWindows() + "/" + tilePane.MAX_LAYERS * tilePane.MAX_LAYER_NODES);
    }

    public void newWindowSelected(WindowSelectedEvent evt) {
        inputHandler.setSelectedWindow(evt.getIndex());
    }
}
