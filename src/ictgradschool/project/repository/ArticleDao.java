package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    public List<Article> getAllArticles() throws IOException {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(
                        "SELECT article.id, title, content, date_created, author_id, username FROM article " +
                                "LEFT JOIN user ON author_id = user.id ORDER BY article.id DESC;")) {
                    while (resultSet.next()) {
                        articles.add(makeArticle(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private Article makeArticle(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getTimestamp(4).toLocalDateTime(),
                resultSet.getInt(5),
                resultSet.getString(6)
        );
    }

    public Article getArticleById(int id) throws IOException {
        Article article = null;
        try (Connection connection = DBConnectionUtils.getConnection()) {
            article = getArticleById(connection, id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    private Article getArticleById(Connection connection, int id) throws SQLException {
        Article article = null;
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT article.id, title, content, date_created, author_id, username FROM article " +
                        "LEFT JOIN user ON author_id = user.id WHERE article.id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    article = makeArticle(rs);
                }
            }
        }
        return article;
    }

    public List<Article> getArticleByUserId(int authorId) throws IOException {
        List<Article> articleList = new ArrayList<>();
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT article.id, title, content, date_created, author_id, username FROM article " +
                            "LEFT JOIN user ON author_id = user.id WHERE author_id = ? ORDER BY article.id DESC;")) {
                stmt.setInt(1, authorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        articleList.add(makeArticle(rs));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public Article postNewArticle(String title, String content, int authorId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO article (title, content, author_id, date_created) VALUES (?, ?, ?, ?)")) {
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setInt(3, authorId);
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                ps.executeQuery();
            }
            int id = DaoUtil.getLastInsertedId(connection);
            return getArticleById(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Article updateArticle(String title, String content, int id) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE article SET title = ?, content = ? WHERE id = ?;")) {
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setInt(3, id);
                ps.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getArticleById(id);
    }

    public void deleteArticle(int articleId) throws IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM comment WHERE article_id = ? ORDER BY id DESC;")) {
                stmt.setInt(1, articleId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM article WHERE id = ?")) {
                stmt.setObject(1, articleId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserAllArticle(int authorId) throws SQLException, IOException {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT id FROM article WHERE author_id = ?")) {
                stmt.setInt(1, authorId);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        deleteArticle(resultSet.getInt(1));
                    }
                }
            }
        }
    }

}
