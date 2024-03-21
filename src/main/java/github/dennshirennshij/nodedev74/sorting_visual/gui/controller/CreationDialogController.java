package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.event.deliver.CreationAppliedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.CreationDialog;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class CreationDialogController {

    @FXML
    public CreationDialog dialog;

    @FXML
    public SplitMenuButton algorithmPicker;

    @FXML
    public void initialize() {
        AlgorithmLoader loader = AlgorithmLoader.getInstance();
        String[] names = loader.getAlgorithmNames();

        for (String name : names) {
            MenuItem item = new MenuItem(name);
            item.addEventHandler(ActionEvent.ACTION, this::SetAlgorithm);

            algorithmPicker.getItems().add(item);
        }
    }

    public void SetAlgorithm(ActionEvent evt) {
        MenuItem item = (MenuItem) evt.getTarget();
        algorithmPicker.setText(item.getText());
    }

    public void applyDialog() {
        String algoName = algorithmPicker.getText();

        dialog.fireEvent(new CreationAppliedEvent(algoName));
    }
}
