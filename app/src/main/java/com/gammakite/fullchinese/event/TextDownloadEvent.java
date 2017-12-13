package com.gammakite.fullchinese.event;

/**
 * Created by stone on 17-12-13.
 */

public class TextDownloadEvent extends BaseEvent {

    public long id;
    public boolean completed;

    public TextDownloadEvent(long id) {
        super("TextDownloadEvent");
        this.id = id;
        this.completed = false;
    }

    public TextDownloadEvent(long id, boolean completed) {
        super("TextDownloadEvent");
        this.id = id;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format("TextDownloadEvent id %d %s", id, completed ? "completed" : "downloading");
    }
}
