package io.minetweak.event.bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Implementation of the JPower Event Bus
 */
public class EventBus {
    protected final List<RegisteredHandler> handlers;

    public EventBus() {
        handlers = new ArrayList<RegisteredHandler>();
    }

    public void register(final Object object) {
        handlers.add(new RegisteredHandler(object).setAnnotationType(Subscribe.class).registerMethods());
    }

    public boolean unregister(final Object object) {
        RegisteredHandler handlerToRemove = null;
        for (RegisteredHandler handler : handlers) {
            if (handler.getObject() == object) {
                handlerToRemove = handler;
            }
        }
        if (handlerToRemove == null) {
            return false;
        }
        handlers.remove(handlerToRemove);
        return true;
    }

    public void post(final Object event) {
        for (RegisteredHandler h : handlers) {
            h.executeEvent(event);
        }
    }
}