package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.event.CreationAppliedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.CreationSuccess;
import github.dennshirennshij.nodedev74.sorting_visual.gui.layout.WindowLayout;
import github.dennshirennshij.nodedev74.sorting_visual.gui.node.CreationDialog;
import github.dennshirennshij.nodedev74.sorting_visual.gui.node.MainWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public void addNewTile() {
        currentDialog = new Dialog<>();
        currentDialog.setTitle("Create Sorting Window");
        currentDialog.setDialogPane(new CreationDialog());

        Stage stage = (Stage) currentDialog.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        currentDialog.getDialogPane().addEventHandler(CreationAppliedEvent.EVENT_TYPE, this::gotTileData);
        Root.addEventHandler(CreationSuccess.EVENT_TYPE, this::creationOk);

        currentDialog.show();
    }

    public void creationOk(CreationSuccess evt) {
        if(tilePane.isFull()) {
            addWindowButton.setDisable(true);
        }
        windowCounter.setText(tilePane.countWindows() + "/" + tilePane.MAX_LAYERS * tilePane.MAX_LAYER_NODES);

        currentDialog.setResult(Boolean.TRUE);
        currentDialog.close();
    }

    public void gotTileData(CreationAppliedEvent evt) {
        AlgorithmLoader loader = AlgorithmLoader.getInstance();
        Class<?extends Algorithm> algo = loader.getAlgorithmClassByName(evt.getAlgorithm());

        Root.createSortingWindowTile(algo);
    }
}
