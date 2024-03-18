package github.dennshirennshij.nodedev74.sorting_visual.event;

import javafx.event.Event;
import javafx.event.EventType;

public class CheckCountChangeEvent extends Event {

    public static final EventType<CheckCountChangeEvent> EVENT_TYPE = new EventType<>(Event.ANY,"CheckCountEvent");

    private int value;

    public CheckCountChangeEvent(int value) {
        super(EVENT_TYPE);

        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
