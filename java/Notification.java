package com.example.practice2;

public class Notification {
    public static final int HOLIDAY_REQUEST = 0;
    public static final int PROFILE_UPDATE = 1;

    private int type;
    private String title;
    private String message;
    private String timestamp;
    private boolean isRead;

    // Constructor
    public Notification(int type, String title, String message, String timestamp, boolean isRead) {
        this.type = type;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

    // Getters
    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    // Setters
    public void setRead(boolean read) {
        isRead = read;
    }
}
