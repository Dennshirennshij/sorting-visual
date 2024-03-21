package github.dennshirennshij.nodedev74.sorting_visual.event.window;

import javafx.event.Event;
import javafx.event.EventType;

public class WindowIndexUpdateEvent extends Event {

    public static final EventType<WindowIndexUpdateEvent> EVENT_TYPE = new EventType<>(Event.ANY, "WindowIndexUpdate");

    private final int removedIndex;

    public WindowIndexUpdateEvent(int removedIndex) {
        super(EVENT_TYPE);

        this.removedIndex = removedIndex;
    }

    public int getRemovedIndex() {
        return this.removedIndex;
    }
}
