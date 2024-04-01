package com.example.posproject;

public class Activity {
    public int id;
    public String activity;
    public String user;
    public String datetime;
    public String description;

    public Activity(int id, String activity, String user, String datetime, String description) {
        this.id = id;
        this.activity = activity;
        this.user = user;
        this.datetime = datetime;
        this.description = description;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public String getUser() {
        return user;
    }

    public String getDatetime() {
        return datetime;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}