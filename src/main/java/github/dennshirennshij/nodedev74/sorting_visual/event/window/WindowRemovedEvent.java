package github.dennshirennshij.nodedev74.sorting_visual.event.window;

import javafx.event.Event;
import javafx.event.EventType;

public class WindowRemovedEvent extends Event {

    public static final EventType<WindowRemovedEvent> EVENT_TYPE = new EventType<>(Event.ANY, "WindowRemoved");

    public WindowRemovedEvent() {
        super(EVENT_TYPE);
    }
}
