package github.dennshirennshij.nodedev74.sorting_visual.gui.node;

import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmFinishedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmInitEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.CheckCountChangeEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.SwapCountChangedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;

public class SortingWindow extends HBox {

    private SortingDisplay display;
    private Algorithm algorithm;

    private int index;

    /* Counter values */

    private int swapCounter = 0;
    private int checkCount = 0;

    /* Constructors */

    public SortingWindow() {
        try {
            URL file = getClass().getClassLoader().getResource("fxml/element/SortingWindow.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml");
        }
    }
    public SortingWindow(Class<? extends Algorithm> algorithm, int index) {
        this();

        this.index = index;

        try {
            this.algorithm = algorithm.getDeclaredConstructor(SortingWindow.class).newInstance(this);
            fireEvent(new AlgorithmInitEvent());
        } catch (Exception e) {
            System.out.println("Couldnt load algorithm" + e.getMessage());
        }
    }

    /* Starting the Algorithm */

    public void start(int[] array) {
        this.currentWindowState = WindowState.RUNNING;

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                algorithm.start(array);

                Platform.runLater(() -> fireEvent(new AlgorithmFinishedEvent()));
                return null;
            }
        };

        new Thread(task).start();
    }

    public Algorithm getAlgorithm() {
        return this.algorithm;
    }

    /* Sorting Display Stuff */

    public SortingDisplay getSortingDisplay() {
        return this.display;
    }

    public void setSortingDisplay(SortingDisplay display) {
        this.display = display;
    }


    /* Interface from Algorithm */

    public void trade(int listIndex, int i1, int i2) {
        Platform.runLater(() -> {
            System.out.println("Trade " + i1 + " with " + i2 + " in list " + listIndex);
            display.swap(listIndex, i1, i2);
            fireEvent(new SwapCountChangedEvent(++swapCounter));
        });
    }

    public void set(int listIndex, int index, int value) {
        System.out.println("Set " + value + " at " + index + " in list " + listIndex);
    }

    public void get(int listIndex, int index) {
        Platform.runLater(() -> {
            System.out.println("Get " + index + " in list " + listIndex);

            fireEvent(new CheckCountChangeEvent(++checkCount));
        });
    }

    public int getIndex() {
        return this.index;
    }

    public void addVisualList(List<Integer> visualList) {
        Platform.runLater(() -> display.addVisualList(visualList));
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



    /* Halting/Pausing Logic */

    private long continueAt = 0;
    private static final long defaultCooldown = 1000;
    private long currentCooldown = 1000;

    private WindowState currentWindowState;

    public void changeCooldown(float factor) {
        currentCooldown = Math.round(defaultCooldown / factor);
    }

    public void cooldown () {
        continueAt = System.currentTimeMillis() + currentCooldown;
    }

    public boolean isHalted () {
        return System.currentTimeMillis() < continueAt || isPaused() || !isRunning();
    }

    public boolean isRunning() {
        return currentWindowState == WindowState.RUNNING;
    }

    public boolean isPaused() {
        return currentWindowState == WindowState.PAUSED;
    }

    public void togglePause () {
        if (isPaused()) {
            continueAt = System.currentTimeMillis();
            setCurrentWindowState(WindowState.RUNNING);
        } else {
            setCurrentWindowState(WindowState.PAUSED);
        }
    }

    public enum WindowState
    {
        PAUSED,
        RUNNING,
        STOPPED
    }

    public WindowState getCurrentWindowState() {
        return currentWindowState;
    }

    public void setCurrentWindowState(WindowState newState) {
        currentWindowState = newState;
    }

}
