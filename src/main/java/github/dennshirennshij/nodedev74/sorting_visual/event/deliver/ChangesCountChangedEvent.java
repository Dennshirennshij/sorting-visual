package github.dennshirennshij.nodedev74.sorting_visual.event.deliver;

import javafx.event.Event;
import javafx.event.EventType;

public class ChangesCountChangedEvent extends Event {

    public static final EventType<ChangesCountChangedEvent> EVENT_TYPE = new EventType<>(Event.ANY,"SwapCountChange");
    private int value;

    public ChangesCountChangedEvent(int newValue) {
        super(EVENT_TYPE);

        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }
}
