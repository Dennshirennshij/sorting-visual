package github.dennshirennshij.nodedev74.sorting_visual.gui.node;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerControl extends Label {

    private final Timer timer = new Timer();

    private final TimerTask timerTask;

    private int speed = 1;
    private boolean isPaused = false;

    private long startTime;

    private long timeStore;

    public TimerControl() {
        timerTask = new TimerTask() {
            public void run() {
                Date time = new Date((System.currentTimeMillis() - startTime) * speed);
                Platform.runLater(() -> setText(time.getMinutes() + ":" + time.getSeconds()));
            }
        };
    }

    public void start() {
        startTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(timerTask,0, 100l);
    }

    public void stop() {
        timerTask.cancel();
    }

    public void speedUp(float factor) {

    }

    public void togglePause() {
        isPaused = !isPaused;

        if(isPaused) {
            timerTask.cancel();
            timeStore = System.currentTimeMillis();
        } else {
            startTime = System.currentTimeMillis();
            timer.scheduleAtFixedRate(timerTask, new Date(timeStore), 100l);
        }
    }
}
