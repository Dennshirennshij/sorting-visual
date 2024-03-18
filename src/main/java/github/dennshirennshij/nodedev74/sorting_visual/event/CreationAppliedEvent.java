package github.dennshirennshij.nodedev74.sorting_visual.event;

import javafx.event.Event;
import javafx.event.EventType;

public class CreationAppliedEvent extends Event {

    public static final EventType<CreationAppliedEvent> EVENT_TYPE = new EventType<>(javafx.event.Event.ANY,"CreationApplied");

    private final String algorithmName;

    public CreationAppliedEvent(String algorithmName) {
        super(EVENT_TYPE);

        this.algorithmName = algorithmName;
    }

    public String getAlgorithm() {
        return this.algorithmName;
    }
}
