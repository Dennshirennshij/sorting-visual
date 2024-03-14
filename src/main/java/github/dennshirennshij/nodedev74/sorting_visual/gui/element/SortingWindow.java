package github.dennshirennshij.nodedev74.sorting_visual.gui.element;

import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.AlgorithmWindowInterface;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.VisualList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.net.URL;

public class SortingWindow extends BorderPane implements AlgorithmWindowInterface {

    @Override
    public void trade(int listIndex, int i1, int i2) {
        System.out.println("Trade " + i1 + " with " + i2 + " in list " + listIndex);
    }

    @Override
    public void set(int listIndex, int index, int value) {
        System.out.println("Set " + value + " at " + index + " in list " + listIndex);
    }

    @Override
    public void get(int listIndex, int index) {
        System.out.println("Get " + index + " in list " + listIndex);
    }

    @Override
    public void addVisualList(VisualList visualList) {
        System.out.println("Add visual list");
    }

    @Override
    public boolean isPaused() {
        return false;
    }

    @Override
    public void getLength(int listIndex) {
        System.out.println("Get length of list " + listIndex);
    }

    @Override
    public void removeVisualList(int listIndex) {
        System.out.println("Remove visual list " + listIndex);
    }

    @Override
    public void getArray(int listIndex) {
        System.out.println("Get array of list " + listIndex);
    }

    public void start(int[] elements) {
        algorithm.start(elements);
    }

    public enum WindowState
    {
        PAUSED,
        RUNNING,
        STOPPED
    }

    private WindowState currentWindowState;

    private Algorithm algorithm;

    /* Constructors */

    public SortingWindow() {
        try {
            URL file = getClass().getClassLoader().getResource("fxml/SortingWindow.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml");
        }
    }
    public SortingWindow(Class<? extends Algorithm> algorithm) {
        this();
        try {
            this.algorithm = algorithm.getDeclaredConstructor(AlgorithmWindowInterface.class).newInstance(this);
        } catch (Exception e) {
            System.out.println("Couldnt load algorithm");
        }
        // todo: impl initial logic
    }

    public WindowState getCurrentWindowState() {
        return currentWindowState;
    }

    public void setCurrentWindowState(WindowState newState) {
        currentWindowState = newState;
    }
}
