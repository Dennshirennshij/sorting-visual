package github.dennshirennshij.nodedev74.sorting_visual.gui.view;

import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowCreatedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.layout.WindowLayout;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting.SortingWindow;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

public class MainWindow extends BorderPane {

    private final ArrayList<SortingWindow> sortingWindows;

    private int selectedWindow;

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

        SortingWindow sortingWindow = new SortingWindow(algo, sortingWindows.size());

        tilePane.addWindow(sortingWindow);
        sortingWindows.add(sortingWindow);

        fireEvent(new WindowCreatedEvent());
    }

    public void removeSortingWindow(int index) {
        WindowLayout tilePane = (WindowLayout) lookup("#tilePane");
        tilePane.removeWindow(index);

        sortingWindows.remove(index);
    }

    public ArrayList<SortingWindow> getSortingWindows() {
        return this.sortingWindows;
    }

    public void setSelectedWindow(int index) {
        this.selectedWindow = index;
    }

    public SortingWindow getSelectedWindow() {
        return sortingWindows.get(selectedWindow);
    }
}
