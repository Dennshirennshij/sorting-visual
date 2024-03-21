package github.dennshirennshij.nodedev74.sorting_visual.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;

import java.net.URL;

public class CreationDialog extends DialogPane {

    public CreationDialog() {
        try {
            URL file = getClass().getClassLoader().getResource("fxml/element/CreationDialog.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml");
        }
    }
}
