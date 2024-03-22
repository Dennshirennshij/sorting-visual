package github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting;

import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmFinishedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.algorithm.AlgorithmInitEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.deliver.CheckCountChangeEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.deliver.ChangesCountChangedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowIndexUpdateEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowRemovedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.event.window.WindowStateChangedEvent;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.MainWindow;
import github.dennshirennshij.nodedev74.sorting_visual.gui.view.input.InputHandler;
import github.dennshirennshij.nodedev74.sorting_visual.sorting.Algorithm;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;

public class SortingWindow extends HBox {

    private Thread thread;

    private SortingDisplay display;
    private Algorithm algorithm;

    private int index;

    /* Counter values */

    private int swapCounter = 0;
    private int checkCount = 0;

    /* Constructors */

    public SortingWindow() {
        try {
            URL file = getClass().getClassLoader().getResource("fxml/view/SortingWindow.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            loader.setRoot(this);
            loader.load();
        } catch(Exception e) {
            System.out.println("Couldnt load fxml " + e.getMessage());
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
        setCurrentWindowState(WindowState.RUNNING);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                    algorithm.start(array);
                    return null;
            }
        };
        thread = new Thread(task);
        thread.start();

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                setCurrentWindowState(WindowState.FINISHED);
                fireEvent(new AlgorithmFinishedEvent());
            }
        });
    }

    public void killTask() {
        if(thread != null && thread.isAlive()) {
            try {
                thread.stop();
            } catch(Exception e) { }
        }
    }

    public void destroy() {
        InputHandler inputHandler = (InputHandler) getScene().lookup("#inputHandler");
        inputHandler.removeHandler(this.index);

        MainWindow window = (MainWindow) getScene().lookup("#Root");
        window.removeSortingWindow(this.index);

        window.getSortingWindows().forEach((win) -> {
            win.fireEvent(new WindowIndexUpdateEvent(this.index));
        });

        window.fireEvent(new WindowRemovedEvent());
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

    public void trade(int listIndex) {
        Platform.runLater(() -> {
            display.renderChanges(listIndex);
            fireEvent(new ChangesCountChangedEvent(++swapCounter));
        });
    }

    public void set(int listIndex) {
        Platform.runLater(() -> {
            display.renderChanges(listIndex);
            fireEvent(new ChangesCountChangedEvent(++swapCounter));
        });
    }

    public void get(int listIndex, int index) {
        Platform.runLater(() -> {
            fireEvent(new CheckCountChangeEvent(++checkCount));
        });
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addVisualList(List<Integer> visualList) {
        Platform.runLater(() -> display.addVisualList(visualList));
    }

    public void removeVisualList(int listIndex) {
        Platform.runLater(() -> display.removeVisualList(listIndex));
    }



    /* Halting/Pausing Logic */

    private long continueAt = 0;
    private static final long defaultCooldown = 500;
    private long currentCooldown = 500;

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
        STOPPED,
        FINISHED
    }

    public WindowState getCurrentWindowState() {
        return currentWindowState;
    }

    public void setCurrentWindowState(WindowState newState) {
        WindowState old = currentWindowState;
        currentWindowState = newState;
        fireEvent(new WindowStateChangedEvent(old, newState));
    }

}
