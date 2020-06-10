package ictgradschool.project.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;

public class Article {
    public int id;
    public String title;
    public String content;
    public long date;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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

    public static void main(String[] args) throws JsonProcessingException {
        Article article = new Article();
        article.id = 1234;
        article.title = "Some title";
        article.content = "Some content";
        article.dateCreated = LocalDateTime.now();
        String json = new ObjectMapper().writeValueAsString(article);
        System.out.println(json);
    }
}
