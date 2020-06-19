package ictgradschool.project.entity;

import java.io.Serializable;

public class UsernameAvailability implements Serializable {
    private String username;
    private boolean available;

    public UsernameAvailability() {
    }

    public UsernameAvailability(String username, boolean available) {
        this.username = username;
        this.available = available;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
