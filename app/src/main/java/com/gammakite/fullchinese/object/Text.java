package com.gammakite.fullchinese.object;

/**
 * Created by stone on 17-12-9.
 */

public class Text {
    private long id;
    private String title;
    private String type;
    private String date;
    private boolean isCloud;
    private boolean isDownloading;

    public Text(long id, String title, String type, String date, boolean isCloud) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date = date;
        this.isCloud = isCloud;
        this.isDownloading = false;
    }

    public long getId() {
        return id;
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

    public void setIsCloud(boolean isCloud) {
        this.isCloud = isCloud;
    }

    public boolean getIsCloud() {
        return isCloud;
    }

    public void setIsDownloading(boolean downloading) {
        this.isDownloading = downloading;
    }

    public boolean getIsDownloading() {
        return isDownloading;
    }
}
