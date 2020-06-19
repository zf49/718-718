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
    public Comment insertNewComment(String content, int authorId, int articleId, int parentId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            int level = getLevelByParentId(connection, parentId);
            assert level <= 2;
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO comment (content, " +
                    "date_created, author_id, article_id, level, parent_id) VALUES (?, NOW(), ?, ?, ?, ?);")) {
                statement.setString(1, content);
                statement.setInt(2, authorId);
                statement.setInt(3, articleId);
                statement.setInt(4, level);
                if (parentId == 0) {
                    statement.setNull(5, Types.INTEGER);
                } else {
                    statement.setInt(5, parentId);
                }
                statement.executeUpdate();
            }
            int newId = DaoUtil.getLastInsertedId(connection);
            return getCommentById(connection, newId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getLevelByParentId(Connection connection, int parentId) {
        int level = 0;
        if (parentId != 0) {
            Comment parentComment = getCommentById(connection, parentId);
            level = parentComment.getLevel() + 1;
        }
        return level;
    }

    public Comment getCommentById(int id) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            return getCommentById(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Comment getCommentById(Connection connection, int id) {
        Comment comment = new Comment();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT comment.id, content, date_created, author_id, article_id, username, level, parent_id " +
                        "FROM comment LEFT JOIN user ON comment.author_id = user.id " +
                        "WHERE comment.id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    comment = makeComment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    private Comment makeComment(ResultSet resultSet) throws SQLException {
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

    public List<Comment> getCommentsByArticleId(int articleId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            return getCommentsByArticleId(connection, articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Comment> getCommentsByArticleId(Connection connection, int articleId) throws SQLException {
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
            Comment comment = makeComment(resultSet);
            map.put(comment.getId(), comment);
            if (comment.hasParent()) {
                Comment parentComment = map.get(comment.getParentId());
                parentComment.addChild(comment);
            } else if (comment.getLevel() == 0) {
                comments.add(comment);
            } else {
                // Comments without parent and level > 0 is not showing to the user
            }
        }
        return comments;
    }

    public void deleteCommentById(int commentId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            removeReferenceToComment(connection, commentId);
            deleteComment(connection, commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeReferenceToComment(Connection connection, int commentId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE comment SET parent_id = null WHERE parent_id = ?")) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        }
    }

    private void deleteComment(Connection connection, int commentId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM comment WHERE id = ?;")) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
