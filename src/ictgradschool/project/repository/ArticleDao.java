package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.entity.User;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private Connection connection;

    public ArticleDao() {
        try {
            this.connection = DBConnectionUtils.getConnectionFromClasspath("database.properties");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getAllArticles() throws IOException {
        List<Article> articles = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(

                    "SELECT id, title, content, date_created, author_id FROM article")) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String content = resultSet.getString(3);
                    LocalDateTime dateCreated = resultSet.getTimestamp(4).toLocalDateTime();
                    int authorId = resultSet.getInt(5);
                    Article article = new Article(id, title, content, dateCreated, authorId);
                    articles.add(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;

    }

    public List<Article> getArticleByUserId(int authorId) {
        List<Article> articleList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, title, content, author_id, date_created FROM article WHERE author_id = ?")) {
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
                "UPDATE article SET title=?,content=?,date_created=? WHERE id=?;")) {
            ps.setString(1, article.title);
            ps.setString(2, article.content);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // TODO update time?
            ps.setInt(4, article.id);
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


    public void deleteUserAllArticle(int authorId) throws SQLException, IOException {
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


        public static void main(String[] args) throws IOException, SQLException {
        ArticleDao articleDao = new ArticleDao();
//        for(Article a : articleDao.getAllArticles()){
//            System.out.println(a.title);
//        }
//            System.out.println( articleDao.getUser("Antonette"));
    }

}
