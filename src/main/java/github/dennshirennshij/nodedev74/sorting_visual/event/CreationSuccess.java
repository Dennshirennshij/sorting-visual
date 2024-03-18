package github.dennshirennshij.nodedev74.sorting_visual.event;

import javafx.event.Event;
import javafx.event.EventType;

public class CreationSuccess extends Event {

    public static final EventType<CreationSuccess> EVENT_TYPE = new EventType<>(javafx.event.Event.ANY,"CreationSuccess");

    public CreationSuccess() {
        super(EVENT_TYPE);
    }
}
