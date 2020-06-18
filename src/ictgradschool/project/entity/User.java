package ictgradschool.project.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String username;

    private String avatarName;
    // TODO: rename to `firstName` and `lastName`
    private String fname;
    private String lname;
    private Date dateBirth;
    private String description;

    private int detailId;

    public User() {}

    // TODO: remove salt and hash parameters
    public User(int id, String username, String salt, String passwordHash, String avatarName) {
        this.id = id;
        this.username = username;
        this.avatarName = avatarName;
    }

    public User(int id, String username, String avatarName) {
        this.id = id;
        this.username = username;
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

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarPath() {
        return "avatar/" + avatarName;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (dateBirth == null) {
            return "";
        }
        return format.format(dateBirth);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatarName='" + avatarName + '\'' +
                '}';
    }
}
