package github.dennshirennshij.nodedev74.sorting_visual.event.algorithm;

import javafx.event.EventType;
import javafx.event.Event;

public class AlgorithmInitEvent extends Event {
    public static final EventType<AlgorithmInitEvent> EVENT_TYPE = new EventType<>(javafx.event.Event.ANY,"AlgorithmInit");

    public AlgorithmInitEvent() {
        super(EVENT_TYPE);
    }
}
