package com.gammakite.fullchinese.event;

/**
 * Created by stone on 17-12-13.
 */

public class BaseEvent {
    public BaseEvent(String event) {
        this.event = event;
    }

    public String event;

    boolean equals(String event) {
        return event.equalsIgnoreCase(event);
    }

    @Override
    public String toString() {
        return String.format("BaseEvent %s", event);
    }
}
