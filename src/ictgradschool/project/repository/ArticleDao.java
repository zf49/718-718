package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArticleDao {
    private Connection connection;

    public ArticleDao() {
        try {
            this.connection = DBConnectionUtils.getConnectionFromClasspath("database.properties");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT article.id AS article_id, article.title, article.content, article.date_created, article.author_id, user.id, username FROM article right JOIN user ON author_id = user.id ORDER BY id DESC")) {
                while (resultSet.next()) {
                     articles.add(getArticle(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private Article getArticle(ResultSet resultSet) throws SQLException {
        Article article =  new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                resultSet.getTimestamp(4).toLocalDateTime(), resultSet.getInt(5),resultSet.getString(7));
        article.getBriefContent();
        System.out.println(article);
        return article;
    }

    public List<Article> getArticleByUserId(int authorId) {
        List<Article> articleList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, title, content, author_id, date_created FROM article WHERE author_id = ? ORDER BY id DESC")) {
            stmt.setInt(1, authorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    articleList.add(getArticleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public Article getArticleById(int id) {
        Article article = null;
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, title, content, author_id, date_created FROM article WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    article = getArticleFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    private Article getArticleFromResultSet(ResultSet rs) throws SQLException {
        return new Article(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getTimestamp(5).toLocalDateTime(),
                rs.getInt(4)
        );
    }

    public Article postNewArticle(Article article) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO article (title, content,author_id,date_created) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, article.title);
            ps.setString(2, article.content);
            ps.setInt(3, article.authorId);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeQuery();
        }
        article.id = DaoUtil.getLastInsertedId(connection);
        return getArticleById(article.id);
    }

    public Article updateArticle(Article article) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE article SET title=?,content=? WHERE id=?;")) {
            ps.setString(1, article.title);
            ps.setString(2, article.content);
            ps.setInt(3, article.id);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getArticleById(article.id);
    }

    public void deleteOneArticle(int articleId) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM comment WHERE article_id = ?;")) {
            stmt.setInt(1, articleId);
            stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM article WHERE id = ?")) {
            stmt.setObject(1, articleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserAllArticle(int authorId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT id FROM article WHERE author_id = ?")) {
            stmt.setInt(1, authorId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    deleteOneArticle(resultSet.getInt(1));
                }
            }
        }
    }


}
