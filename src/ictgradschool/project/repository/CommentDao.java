package ictgradschool.project.repository;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CommentDao {
    private Connection connection;

    public CommentDao() {
        try {
            this.connection =  DBConnectionUtils.getConnectionFromClasspath("database.properties");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Comment getCommentFromResultSet(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getInt(4),
                rs.getInt(5),
                rs.getInt(1),
                rs.getString(2),
                rs.getTimestamp(3).toLocalDateTime(),
                rs.getString(6)
        );
    }

    public List<Comment> getCommentsByArticleId(int targetArticleId) throws SQLException {
        List<Comment> comments = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT comment.id, content, date_created, author_id, article_id, user.username AS author_name " +
                        "FROM comment LEFT OUTER JOIN user ON comment.author_id = user.id WHERE comment.article_id = ?;")) {
            statement.setInt(1, targetArticleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comments.add(getCommentFromResultSet(resultSet));
                }
            }
        }
        return comments;
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

    private Comment getCommentById(int commentId) throws SQLException {
        Comment comment = new Comment();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT comment.id, content, date_created, author_id, article_id, user.username AS author_name " +
                        "FROM comment LEFT OUTER JOIN user ON comment.author_id = user.id WHERE comment.id = ?;")) {
            statement.setInt(1, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    comment = getCommentFromResultSet(resultSet);
            }
        }
        return comment;
    }

    public void deleteComment(int commentId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM comment WHERE id = ?;")) {
            statement.setInt(1, commentId);
            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
