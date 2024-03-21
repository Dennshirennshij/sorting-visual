package github.dennshirennshij.nodedev74.sorting_visual.event.window;

import javafx.event.Event;
import javafx.event.EventType;

public class WindowSelectedEvent extends Event {

    public static final EventType<WindowSelectedEvent> EVENT_TYPE = new EventType<>(Event.ANY,"WindowSelected");

    private final int index;

    public WindowSelectedEvent(int index) {
        super(EVENT_TYPE);

        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
