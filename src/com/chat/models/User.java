package com.chat.models;

import java.io.Serializable;

/**
 * User model to represent connected clients.
 */
public class User implements Serializable {

    private String id;      // Unique ID (could be socket ID or UUID)
    private String name;    // Display name
    private boolean online; // Online status

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.online = true;
    }

    // Getters & Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isOnline() { return online; }
    public void setOnline(boolean online) { this.online = online; }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', online=" + online + "}";
    }
}
