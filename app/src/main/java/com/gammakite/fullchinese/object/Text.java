package com.gammakite.fullchinese.object;

/**
 * Created by stone on 17-12-9.
 */

public class Text {
    private String title;
    private String type;
    private String date;
    private boolean isCloud;

    public Text(String title, String type, String date, boolean isCloud) {
        this.title = title;
        this.type = type;
        this.date = date;
        this.isCloud = isCloud;
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

    public boolean getIsCloud() {
        return isCloud;
    }
}
