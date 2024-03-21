package github.dennshirennshij.nodedev74.sorting_visual.gui.view;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerControl extends Label {

    private final Timer timer;

    private final TimerTask timerTask;
    private boolean isPaused;

    private long startTime;

    private long timeStore;

    public TimerControl() {
        isPaused = true;
        timer = new Timer();

        timerTask = new TimerTask() {
            public void run() {
                if(!isPaused) {
                    long time = (System.currentTimeMillis()) - startTime;
                    Date date = new Date(time);

                    Platform.runLater(() -> setText("Time: " + (date.getHours() - 1) + ":" + date.getMinutes() + ":" + date.getSeconds()));
                }
            }
        };
    }

    public void start() {
        isPaused = false;
        startTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(timerTask,0, 1000L);
    }

    public void stop() {
        timer.cancel();
        timerTask.cancel();
    }

    public void togglePause() {
        if(isPaused) {
            startTime = System.currentTimeMillis() - (timeStore - startTime);
            isPaused = false;
        } else {
            timeStore = System.currentTimeMillis();
            isPaused = true;
        }
    }
}
