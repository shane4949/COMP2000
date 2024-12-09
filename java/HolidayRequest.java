package com.example.practice2;

import java.io.Serializable;

public class HolidayRequest implements Serializable {
    private String firstName;
    private String lastName;
    private String holidayStart;
    private String holidayEnd;
    private int profileImage;
    private int totalDays;
    private String reason;
    private String attachmentUrl;

    public HolidayRequest(String firstName, String lastName, String holidayStart, String holidayEnd, int profileImage, int totalDays, String reason, String attachmentUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.holidayStart = holidayStart;
        this.holidayEnd = holidayEnd;
        this.profileImage = profileImage;
        this.totalDays = totalDays;
        this.reason = reason;
        this.attachmentUrl = attachmentUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHolidayStart() {
        return holidayStart;
    }

    public String getHolidayEnd() {
        return holidayEnd;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public String getReason() {
        return reason;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }
}
