package github.dennshirennshij.nodedev74.sorting_visual.event;

import javafx.event.Event;
import javafx.event.EventType;

public class SwapCountChangedEvent extends Event {

    public static final EventType<SwapCountChangedEvent> EVENT_TYPE = new EventType<>(Event.ANY,"SwapCountChange");
    private int value;

    public SwapCountChangedEvent(int newValue) {
        super(EVENT_TYPE);

        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }
}
