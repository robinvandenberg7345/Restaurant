package com.example.robin.journal;

import java.io.Serializable;

/**
 * Created by Robin on 28-2-2018.
 */

public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    // constructors of the class
    public JournalEntry(int id, String title, String content, String mood, String timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public JournalEntry(String title, String content, String mood, String timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
