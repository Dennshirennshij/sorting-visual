package github.dennshirennshij.nodedev74.sorting_visual.gui.controller;

import github.dennshirennshij.nodedev74.sorting_visual.exception.IncorrectArraySyntax;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.input.InputTab;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InputTabController {

    @FXML
    public InputTab inputTab;

    @FXML
    public TextArea arrayInput;

    @FXML
    public TextField minValue;

    @FXML
    public TextField maxValue;

    @FXML
    public TextField sizeValue;

    @FXML
    public Button randomArray;

    @FXML
    public void initialize() {
        arrayInput.textProperty().addListener(((observableValue, oldValue, newValue) -> arrayInputChanged(newValue)));
    }

    @FXML
    public void clearInput() {
        arrayInput.clear();
    }

    public void arrayInputChanged(String newValue) {
        try {
            int[] input = InputTab.parseStringToArray(newValue);
            inputTab.setInputArray(input);
        } catch(IncorrectArraySyntax e) {
            inputTab.setInputArray(null);
            // todo: make red outline
        }
    }

    @FXML
    public void randomGenerator() {
        if(!minValue.getText().isEmpty()) {
            if(!maxValue.getText().isEmpty()) {
                if(!sizeValue.getText().isEmpty()) {
                    try {
                        int min = Integer.parseInt(minValue.getText());
                        int max = Integer.parseInt(maxValue.getText());
                        int size = Integer.parseInt(sizeValue.getText());

                        int[] array = InputTab.getRandomArray(min, max, size);
                        String arrayString = InputTab.parseArrayToString(array);
                        arrayInput.setText(arrayString);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        // todo: show that input is wrong
                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }
}
