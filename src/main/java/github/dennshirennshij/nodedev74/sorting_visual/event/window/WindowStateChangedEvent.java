package github.dennshirennshij.nodedev74.sorting_visual.event.window;

import github.dennshirennshij.nodedev74.sorting_visual.gui.view.sorting.SortingWindow;
import javafx.event.Event;
import javafx.event.EventType;

public class WindowStateChangedEvent extends Event {

    public static final EventType<WindowStateChangedEvent> EVENT_TYPE = new EventType<>(Event.ANY, "WindowStateChanged");

    private final SortingWindow.WindowState oldState;
    private final SortingWindow.WindowState newState;

    public WindowStateChangedEvent(SortingWindow.WindowState oldState, SortingWindow.WindowState newState) {
        super(EVENT_TYPE);

        this.oldState = oldState;
        this.newState = newState;
    }

    public SortingWindow.WindowState getOldState() {
        return this.oldState;
    }

    public SortingWindow.WindowState getNewState() {
        return this.newState;
    }
}
