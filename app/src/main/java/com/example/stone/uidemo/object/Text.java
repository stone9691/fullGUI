package com.example.stone.uidemo.object;

/**
 * Created by stone on 17-12-9.
 */

public class Text {
    private String title;
    private String type;
    private String date;

    public Text(String title, String type, String date) {
        this.title = title;
        this.type = type;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
