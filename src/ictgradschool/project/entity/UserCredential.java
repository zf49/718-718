package ictgradschool.project.entity;

public class UserCredential {
    private int id;
    private String username;
    private String salt;
    private String password;

    public UserCredential() {}

    public UserCredential(int id, String username, String salt, String password) {
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
