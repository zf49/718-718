package ictgradschool.project.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;
import java.time.LocalDateTime;

public class Article {
    public int id;
    public String title;
    public String content;
    public int authorId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime dateCreated;


    public Article() {}


    public Article(int id, String title, String content, int authorId, LocalDateTime dateCreated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
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
