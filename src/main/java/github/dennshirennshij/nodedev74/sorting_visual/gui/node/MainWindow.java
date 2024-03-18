package github.dennshirennshij.nodedev74.sorting_visual.gui.node;

import github.dennshirennshij.nodedev74.sorting_visual.event.CreationSuccess;
import github.dennshirennshij.nodedev74.sorting_visual.gui.layout.WindowLayout;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ArrayList;

public class MainWindow extends BorderPane {

    private final ArrayList<SortingWindow> sortingWindows;

    public MainWindow() {
        sortingWindows = new ArrayList<>();

        try {
            URL file = getClass().getClassLoader().getResource("fxml/MainWindow.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml" + e.getMessage());
        }
    }

    public void createSortingWindowTile(Class<?extends Algorithm> algo) {
        WindowLayout tilePane = (WindowLayout) lookup("#tilePane");

        SortingWindow sortingWindow = new SortingWindow(algo);

        tilePane.addWindow(sortingWindow);
        sortingWindows.add(sortingWindow);

        fireEvent(new CreationSuccess());
    }

    public void removeSortingWindow(int index) {
        TilePane tilePane = (TilePane) lookup("#tilePane");

        sortingWindows.remove(index);
    }
}
