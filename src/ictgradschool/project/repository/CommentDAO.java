package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO {
    private Connection connection;

    public CommentDAO() {
        try {
            this.connection =  DBConnectionUtils.getConnectionFromClasspath("connection.properties");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentsForArticle(int targetArticleId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, content, date_created, author_id, article_id FROM comment WHERE article_id = ?;")) {
            statement.setInt(1, targetArticleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Comment> comments = new LinkedList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String content = resultSet.getString(2);
                    //LocalDateTime dateCreated = resultSet.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime dateCreated = resultSet.getTimestamp(3).toLocalDateTime();
                    int authorId = resultSet.getInt(4);
                    int articleId = resultSet.getInt(5);
                    Comment comment = new Comment(authorId, articleId, id, content, dateCreated);
                    comments.add(comment);
                }
                return comments;
            }
        }
    }

    public Comment postNewComment(Comment comment, int articleId) throws SQLException {
        comment.dateCreated = LocalDateTime.now();
        comment.articleId = articleId;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO comment (content, date_created, author_id, article_id) VALUES (?,NOW(),?,?);")) {
            statement.setString(1, comment.content);
            statement.setInt(2, comment.authorId);
            statement.setInt(3, articleId);
            statement.executeQuery();
        }
        try (Statement statement = connection.createStatement()){
            try (ResultSet r = statement.executeQuery("SELECT LAST_INSERT_ID();")){
                comment.id = r.getInt(1);
            }
        }
        return getCommentById(comment.id);
    }

    private Comment getCommentById(int commentId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT content, date_created, author_id, article_id FROM comment WHERE id = ?;")) {
            statement.setInt(1, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Comment comment = new Comment();
                comment.id = commentId;
                comment.content = resultSet.getString(1);
                comment.dateCreated = resultSet.getTimestamp(2).toLocalDateTime();
                //LocalDateTime dateCreated = resultSet.getDate(2).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                comment.authorId = resultSet.getInt(3);
                comment.articleId = resultSet.getInt(4);
                return comment;
            }
        }
    }

    public boolean deleteComment(int commentId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM comment WHERE id = ?;")) {
            statement.setInt(1, commentId);
            statement.executeQuery();
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, SQLException {
        List<Comment> comments = new CommentDAO().getCommentsForArticle(1);
        for (Comment comment : comments)
        System.out.println(comment.toString());
    }

}
