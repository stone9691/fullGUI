package com.gammakite.fullchinese.object;

/**
 * Created by stone on 17-12-9.
 */

public class Sentence {
    private long id;
    private String word;
    private String chinese;
    private String english;

    public Sentence(long id, String word, String chinese, String english) {
        this.id = id;
        this.chinese = chinese;
        this.english = english;
    }

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getChinese() {
        return chinese;
    }

    public String getEnglish() {
        return english;
    }
}
