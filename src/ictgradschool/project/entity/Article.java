package ictgradschool.project.entity;

import java.time.LocalDateTime;

public class Article {
    public int id;
    public String title;
    public String content;
    public long date;
    public LocalDateTime dateCreated;

    public Article() {}

    public Article(int id, String title, String content, LocalDateTime dateCreated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
