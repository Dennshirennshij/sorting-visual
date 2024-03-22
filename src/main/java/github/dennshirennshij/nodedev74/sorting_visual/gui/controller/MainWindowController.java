package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowCreatedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowRemovedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowSelectedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.layout.WindowLayout;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.input.InputHandler;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.MainWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindowController {

    @FXML
    public MainWindow Root;

    @FXML
    public WindowLayout tilePane;

    @FXML
    public MenuButton algorithmPicker;

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
        String algo = algorithmPicker.getText();
        if(!algo.isEmpty()) {
            AlgorithmLoader loader = AlgorithmLoader.getInstance();
            Class<?extends Algorithm> algoClass = loader.getAlgorithmClassByName(algo);
            Root.createSortingWindowTile(algoClass);
        }
    }

    private void updateWindowCreateButton() {
        addWindowButton.setDisable(tilePane.isFull());
    }

    public void creationOk(WindowCreatedEvent evt) {
        inputHandler.addHandler();

        updateWindowCreateButton();
        windowCounter.setText(tilePane.countWindows() + "/" + tilePane.MAX_LAYERS * tilePane.MAX_LAYER_NODES);
    }

    public void windowRemoved(WindowRemovedEvent evt) {
        updateWindowCreateButton();
        windowCounter.setText(tilePane.countWindows() + "/" + tilePane.MAX_LAYERS * tilePane.MAX_LAYER_NODES);
    }

    public void newWindowSelected(WindowSelectedEvent evt) {
        inputHandler.setSelectedWindow(evt.getIndex());
    }

    @FXML
    public void loadAlgorithms() {
        AlgorithmLoader loader = AlgorithmLoader.getInstance();
        String[] names = loader.getAlgorithmNames();

        algorithmPicker.getItems().clear();
        for(String name : names) {
            MenuItem item = new MenuItem(name);

            item.addEventHandler(ActionEvent.ACTION, (evt) -> {
                algorithmPicker.setText(((MenuItem) evt.getSource()).getText());
            });

            algorithmPicker.getItems().add(item);
        }
    }
}
