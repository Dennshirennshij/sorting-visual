package github.dennshirennshij.nodedev74.sorting_visual.event.algorithm;

import javafx.event.Event;
import javafx.event.EventType;

public class AlgorithmFinishedEvent extends Event {

    public static final EventType<AlgorithmFinishedEvent> EVENT_TYPE = new EventType<>(Event.ANY,"AlgorithmFinished");

    public AlgorithmFinishedEvent() {
        super(EVENT_TYPE);
    }
}
