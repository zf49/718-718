package ictgradschool.project.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {
    public int id;
    public String title;
    public String content;
    public int authorId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime dateCreated;
    public String authorName;
    public String date;

    public Article() {}

    public Article(int id, String title, String content,  LocalDateTime dateCreated,int authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
        this.authorName = authorName;
        date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(this.dateCreated);
    }

    public String getBriefContent() {
        if(content.length() <= 20){
            return content;
        }else {
            return content.substring(0, 20) + "...";
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", dateCreated=" + dateCreated +
                ", authorName='" + authorName + '\'' +
                '}';
    }

}
