package com.example.posproject;

public class User {
    public int id;
    public String username;
    public String password;
    public int session;

    public User(int id,String username, String password, int session) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.session = session;
    }
    public int getId() {
        return id;
    }

    public String getUser() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
