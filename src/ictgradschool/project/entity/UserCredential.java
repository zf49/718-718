package ictgradschool.project.entity;

import ictgradschool.project.util.PasswordUtil;

public class UserCredential {
    private int id;
    private String username;
    private String salt;
    private String passwordHash;

    public UserCredential() {}

    public UserCredential(int id, String username, String salt, String passwordHash) {
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + passwordHash + '\'' +
                '}';
    }

    public boolean isExpectedPassword(String password) {
        boolean isPasswordValid = PasswordUtil.isExpectedPassword(
                password.toCharArray(),
                PasswordUtil.base64Decode(salt),
                PasswordUtil.base64Decode(passwordHash)
        );
        return isPasswordValid;
    }
}
