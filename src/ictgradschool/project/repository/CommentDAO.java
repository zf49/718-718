package ictgradschool.project.repository;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// TODO: please rename this to `CommentDao`
public class CommentDAO {
    private Connection connection;

    public CommentDAO() {
        try {
            this.connection =  DBConnectionUtils.getConnectionFromClasspath("database.properties");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentsByArticleId(int targetArticleId) throws SQLException, IOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, content, date_created, author_id, article_id FROM comment WHERE article_id = ?;")) {
            statement.setInt(1, targetArticleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Comment> comments = new LinkedList<>();
                UserDao userDao = new UserDao();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String content = resultSet.getString(2);
                    LocalDateTime dateCreated = resultSet.getTimestamp(3).toLocalDateTime();
                    int authorId = resultSet.getInt(4);
                    int articleId = resultSet.getInt(5);
                    String authorName = userDao.getUserById(authorId).getUsername();
                    Comment comment = new Comment(authorId, articleId, id, content, dateCreated, authorName);
                    comments.add(comment);
                }
                return comments;
            }
        }
    }

    public Comment postNewComment(Comment comment, int articleId) throws IOException, SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO comment (content, date_created, author_id, article_id) VALUES (?,NOW(),?,?);")) {
            statement.setString(1, comment.content);
            statement.setInt(2, comment.authorId);
            statement.setInt(3, articleId);
            statement.executeQuery();
        }
        comment.id = DaoUtil.getLastInsertedId(connection);
        return getCommentById(comment.id);
    }

    private Comment getCommentById(int commentId) throws SQLException, IOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT content, date_created, author_id, article_id FROM comment WHERE id = ?;")) {
            statement.setInt(1, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Comment comment = new Comment();
                comment.id = commentId;
                while (resultSet.next()) {
                    comment.content = resultSet.getString(1);
                    comment.dateCreated = resultSet.getTimestamp(2).toLocalDateTime();
                    comment.authorId = resultSet.getInt(3);
                    comment.articleId = resultSet.getInt(4);
                }
                comment.authorName = new UserDao().getUserById(comment.authorId).getUsername();
                return comment;
            }
        }
    }

    public boolean deleteComment(int commentId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM comment WHERE id = ?;")) {
            statement.setInt(1, commentId);
            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws SQLException, IOException {
        List<Comment> comments = new CommentDAO().getCommentsByArticleId(1);
        for (Comment comment : comments)
        System.out.println(comment.getAuthorName());
    }

}
