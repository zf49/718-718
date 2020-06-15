package ictgradschool.project.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    // TODO: No need to store `salt` and `hash` to session. Move'em to another class
    private String salt;
    private String passwordHash;
    private String avatarName;

    public User() {}

    public User(int id, String username, String salt, String passwordHash) {
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public User(int id, String username, String salt, String passwordHash, String avatarName) {
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.avatarName = avatarName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", avatarName='" + avatarName + '\'' +
                '}';
    }
}
