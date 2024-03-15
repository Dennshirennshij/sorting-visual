package github.dennshirennshij.nodedev74.sorting_visual.gui.element;

import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

import github.dennshirennshij.nodedev74.sorting_visual.sorting.VisualList;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.net.URL;

public class SortingWindow extends BorderPane {

    private SortingDisplay display;
    private Algorithm algorithm;

    private long continueAt = 0;

    public enum WindowState
    {
        PAUSED,
        RUNNING,
        STOPPED
    }

    private WindowState currentWindowState;

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
            this.algorithm = algorithm.getDeclaredConstructor(SortingWindow.class).newInstance(this);
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

    public void start(int[] array) {
        this.currentWindowState = WindowState.RUNNING;

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                algorithm.start(array);
                return null;
            }
        };

        new Thread(task).start();
    }

    public void trade(int listIndex, int i1, int i2) {
        System.out.println("Trade " + i1 + " with " + i2 + " in list " + listIndex);
    }

    public void set(int listIndex, int index, int value) {
        System.out.println("Set " + value + " at " + index + " in list " + listIndex);
    }

    public void get(int listIndex, int index) {
        System.out.println("Get " + index + " in list " + listIndex);
    }

    public void addVisualList(VisualList visualList) {
        System.out.println("Add visual list");
    }

    public boolean isHalted () {
        return System.currentTimeMillis() < continueAt || isPaused();
    }

    public boolean isPaused() {
        return currentWindowState == WindowState.PAUSED;
    }

    public void getLength(int listIndex) {
        System.out.println("Get length of list " + listIndex);
    }

    public void removeVisualList(int listIndex) {
        System.out.println("Remove visual list " + listIndex);
    }

    public void getArray(int listIndex) {
        System.out.println("Get array of list " + listIndex);
    }

    public SortingDisplay getSortingDisplay() {
        return this.display;
    }

    public void setSortingDisplay(SortingDisplay display) {
        this.display = display;
    }

    private long getCooldown () {
        return 1000; // todo
    }

    public void cooldown () {
        continueAt = System.currentTimeMillis() + getCooldown();
    }

    public void togglePause () {
        if (isPaused()) {
            continueAt = System.currentTimeMillis();
            setCurrentWindowState(WindowState.RUNNING);
        } else {
            setCurrentWindowState(WindowState.PAUSED);
        }
    }

}
