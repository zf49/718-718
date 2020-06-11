package ictgradschool.project.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;

public class Comment {
    public int authorId;
    public int articleId;
    public int id;
    public String content;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime dateCreated;

    public Comment() {}

    public Comment(int authorId, int articleId, int id, String content, LocalDateTime dateCreated) {
        this.authorId = authorId;
        this.articleId = articleId;
        this.id = id;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "authorId=" + authorId +
                ", articleId=" + articleId +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
