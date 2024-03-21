package github.dennshirennshij.nodedev74.sorting_visual.event.window;

import javafx.event.Event;
import javafx.event.EventType;

public class WindowCreatedEvent extends Event {

    public static final EventType<WindowCreatedEvent> EVENT_TYPE = new EventType<>(javafx.event.Event.ANY,"WindowCreated");

    public WindowCreatedEvent() {
        super(EVENT_TYPE);
    }
}
