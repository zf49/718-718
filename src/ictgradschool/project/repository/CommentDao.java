package ictgradschool.project.repository;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommentDao {
    private Connection connection;

    public CommentDao() {
        try {
            this.connection = DBConnectionUtils.getConnectionFromClasspath("database.properties");
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
                        "FROM comment LEFT JOIN user ON comment.author_id = user.id WHERE comment.article_id = ?;")) {
            statement.setInt(1, targetArticleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comments.add(getCommentFromResultSet(resultSet));
                }
            }
        }
        return comments;
    }

    public Comment postNewComment(Comment comment, int articleId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO comment (content, date_created, author_id, article_id) VALUES (?,NOW(),?,?);")) {
            statement.setString(1, comment.content);
            statement.setInt(2, comment.authorId);
            statement.setInt(3, articleId);
            // FIXME: executeUpdate ?
            statement.executeQuery();
        }
        comment.id = DaoUtil.getLastInsertedId(connection);
        return getCommentById(comment.id);
    }

    public Comment getCommentById(int commentId) throws SQLException {
        Comment comment = new Comment();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT comment.id, content, date_created, author_id, article_id, user.username AS author_name " +
                        "FROM comment LEFT JOIN user ON comment.author_id = user.id WHERE comment.id = ?;")) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comment insertCommentToComment2(String content, int authorId, int parentId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            Comment parentComment = getCommentById2(connection, parentId);
            int level = parentComment.getLevel() + 1;
            assert level <= 2;
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comment (content, date_created, author_id, article_id, level, parent_id) VALUES (?, NOW(), ?, ?, ?, ?);");
            statement.setString(1, content);
            statement.setInt(2, authorId);
            statement.setInt(3, parentComment.getArticleId());
            statement.setInt(4, level);
            statement.setInt(5, parentId);
            statement.executeUpdate();

            int newId = DaoUtil.getLastInsertedId(connection);

            return getCommentById2(connection, newId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Comment getCommentById2(Connection connection, int id) {
        Comment comment = new Comment();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT comment.id, content, date_created, author_id, article_id," +
                        " user.username, level, parent_id AS author_name " +
                        "FROM comment LEFT JOIN user ON comment.author_id = user.id " +
                        "WHERE comment.id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    comment = makeComment2(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    private Comment makeComment2(ResultSet resultSet) throws SQLException {
        return new Comment(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getTimestamp(3).toLocalDateTime(),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getString(6),
                resultSet.getInt(7),
                resultSet.getInt(8)
        );
    }

    public List<Comment> getCommentsByArticleId2(int articleId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            return getCommentsByArticleId2(connection, articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Comment> getCommentsByArticleId2(Connection connection, int articleId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT comment.id, content, date_created, author_id, article_id, username, level, parent_id " +
                        "FROM comment " +
                        "LEFT JOIN user ON user.id = comment.author_id " +
                        "WHERE article_id = ?")) {
            statement.setInt(1, articleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return makeNestedComments(resultSet);
            }
        }
    }

    private List<Comment> makeNestedComments(ResultSet resultSet) throws SQLException {
        List<Comment> comments = new LinkedList<>();
        Map<Integer, Comment> map = new HashMap<>();
        while (resultSet.next()) {
            Comment comment = makeComment2(resultSet);
            map.put(comment.getId(), comment);
            if (comment.hasParent()) {
                Comment parentComment = map.get(comment.getParentId());
                parentComment.addChild(comment);
            } else {
                comments.add(comment);
            }
        }
        return comments;
    }

    public void deleteCommentById2(int commentId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
