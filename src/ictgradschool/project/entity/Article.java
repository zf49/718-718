package ictgradschool.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article implements Comparable<Article> {
    public int id;
    public String title;
    public String content;
    public int authorId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime dateCreated;
    public String authorName;
    public String date;



    public Article() {}

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Article(int id, String title, String content, LocalDateTime dateCreated, int authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
        date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(this.dateCreated);
    }

    public Article(int id, String title, String content,  LocalDateTime dateCreated,int authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
        this.authorName = authorName;
        date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(this.dateCreated);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBriefContent() {
        if(content.length()<=20){
            return content;
        }else {
            content = content.substring(0, 20) + "...";
            return content;
        }
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

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", authorId=" + authorId +
//                ", dateCreated=" + dateCreated +
//                '}';
//    }

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

    @Override
    public int compareTo(Article article) {
        return article.getId() - this.getId();
    }

    public static void main(String[] args) throws JsonProcessingException {
        Article article = new Article();
        article.id = 1234;
        article.title = "Some title";
        article.content = "Some contentakusdha ukshdklaj hdskajhsd klashd kajhsdlkajh sdkajshkajsh kjaslkdj a";
        article.getBriefContent();
        System.out.println(article);
    }

}
