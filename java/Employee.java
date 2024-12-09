package com.example.practice2;

public class Employee {
    private long id;
    private String name;
    private String position;
    private int profileImageResId;

    public Employee(long id, String name, String position, int profileImageResId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.profileImageResId = profileImageResId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getProfileImageResId() {
        return profileImageResId;
    }
}
