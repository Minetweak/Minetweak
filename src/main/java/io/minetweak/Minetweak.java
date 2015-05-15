package io.minetweak;

import io.minetweak.event.bus.EventBus;

/**
 * @author Logan Gorence
 */
public class Minetweak {

    private static Minetweak minetweak;

    private final EventBus eventBus;

    private Minetweak() {
        eventBus = new EventBus();
        eventBus.register(this);
    }

    public static Minetweak getInstance() {
        if (minetweak == null) {
            minetweak = new Minetweak();
        }
        return minetweak;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

}
