package ictgradschool.project.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Comment implements Serializable {
    private int id;
    private String content;
    private LocalDateTime dateCreated;
    private int authorId;
    private int articleId;
    private String authorName;

    private int level;
    private int parentId;
    private List<Comment> children = new LinkedList<>();

    public Comment() {
    }

    public Comment(int authorId, int articleId, int id, String content, LocalDateTime dateCreated, String authorName) {
        this.authorId = authorId;
        this.articleId = articleId;
        this.id = id;
        this.content = content;
        this.dateCreated = dateCreated;
        this.authorName = authorName;
    }

    public Comment(int id, String content, LocalDateTime dateCreated, int authorId, int articleId,
                   String authorName, int level, int parentId) {
        this.id = id;
        this.content = content;
        this.dateCreated = dateCreated;
        this.authorId = authorId;
        this.articleId = articleId;
        this.authorName = authorName;
        this.level = level;
        this.parentId = parentId;
    }

    public static List<Comment> flatten(List<Comment> comments) {
        List<Comment> results = new LinkedList<>();
        for (Comment comment : comments) {
            addCommentRecursively(results, comment);
        }
        return results;
    }

    /**
     * First add the comment itself, then add its child comments recursively
     *
     * @param results
     * @param comment
     */
    private static void addCommentRecursively(List<Comment> results, Comment comment) {
        results.add(comment);
        for (Comment child : comment.getChildren()) {
            addCommentRecursively(results, child);
        }
    }

    public boolean hasParent() {
        return parentId != 0;
    }

    public void addChild(Comment comment) {
        children.add(comment);
    }

    public List<Comment> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "authorId=" + authorId +
                ", articleId=" + articleId +
                ", id=" + id +
                ", content='" + content.substring(0, Math.min(16, content.length())) + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public String getDate() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(this.dateCreated);
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean canDelete(int userId, Article article) {
        boolean isCommenter = userId == authorId;
        boolean isAuthorOfArticle = userId == article.getAuthorId();
        return isCommenter || isAuthorOfArticle;
    }

    public boolean getCanReply() {
        return level < 2;
    }
}
