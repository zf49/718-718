package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ArticleDao {

    public List<Article> getAllArticles() throws IOException, SQLException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(

                        "SELECT id, title, content, date_created, author_id FROM article")) {
                    List<Article> articles = new LinkedList<>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String content = resultSet.getString(3);
                        LocalDateTime dateCreated = resultSet.getTimestamp(4).toLocalDateTime();
                        int authorId = resultSet.getInt(5);
                        Article article = new Article(id, title, content, dateCreated, authorId);
                        articles.add(article);
                    }
                    return articles;
                }
            }
        }
    }

    public List<Article> getArticleByUserId(int authorId) throws SQLException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT id, title, content, author_id, date_created FROM article WHERE author_id = ?")) {
                stmt.setInt(1, authorId);
                List<Article> articleList = new ArrayList<>();
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        articleList.add(getArticleFromResultSet(rs));
                    }
                }
                if (articleList != null) {
                    return articleList;
                } else {
                    return null;
                }
            }
        }
    }

    public Article getArticleById(int id) throws SQLException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("database.properties")){
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT title, content, date_created, author_id FROM article WHERE id = ?;")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    Article article = new Article();
                    article.id = id;
                    while (resultSet.next()) {
                        article.title = resultSet.getString(1);
                        article.content = resultSet.getString(2);
                        article.dateCreated = resultSet.getTimestamp(3).toLocalDateTime();
                        article.authorId = resultSet.getInt(4);
                    }
                    return article;
                }
            }
        }
    }

    private Article getArticleFromResultSet(ResultSet rs) throws SQLException {
        return new Article(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                rs.getInt(5)
        );
    }


    public Article postNewArticle(Article article) throws SQLException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO article (id,title, content,author_id,date_created) VALUES (?, ?, ?, ?,?)")) {
                    ps.setInt(1, article.id);
                    ps.setString(2, article.title);
                    ps.setString(3, article.content);
                    ps.setInt(4, article.authorId);
                    ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
        return article;
    }


    public Article updateArticle(Article article) throws SQLException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("database.properties")) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE article SET title=?,content=?,date_created=?,author_id=? WHERE id=?;")) {
                ps.setString(1, article.title);
                ps.setString(2, article.content);
                ps.setDate(3, (java.sql.Date) Date.from(article.dateCreated.atZone(ZoneId.systemDefault()).toInstant()));
                ps.setInt(4, article.authorId);
                ps.setInt(5, article.id);
                ps.executeQuery();
            }
            return getArticleById(article.id);
        }
    }

    public void deleteOneArticle(int articleId) throws SQLException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM comment WHERE article_id = ?;")) {
                        statement.setInt(1, articleId);
                        statement.executeQuery();
            }

            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM article WHERE id = ?")) {
                stmt.setObject(1, articleId);
                 stmt.executeUpdate();

            }
        }
    }


    public void deleteUserAllArticle(int authorId) throws SQLException, IOException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            List<Integer> articlesId = new ArrayList<>();
            // get all user's articles which wanna delete
                try (PreparedStatement stmt = connection.prepareStatement(
                        "SELECT id FROM article WHERE author_id = ?")){
                         stmt.setInt(1,authorId);
                    try (ResultSet resultSet = stmt.executeQuery()) {
                        while (resultSet.next()){
                            articlesId.add(resultSet.getInt(1));
                        }
                }
        }
                // delete these comments
             for(int i = 0; i < articlesId.size();i++) {
                 try (PreparedStatement statement = connection.prepareStatement(
                         "DELETE FROM comment WHERE article_id = ?;")) {
                     statement.setInt(1, articlesId.get(i));
                     statement.executeQuery();
                 }
             }
             //delete the articles
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM article WHERE author_id = ?")) {
                stmt.setInt(1, authorId);
                stmt.executeQuery();

            }
        }

    }


    // if someone wanna find an article, just need type several word
//    public List<Article> findArticles(String word) throws IOException, SQLException {
//        if (word == null || word.trim() == "") {
//            return null;
//        }
//        List<Article> articleList = new ArrayList<>();
//        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
//            try (PreparedStatement stmt = connection.prepareStatement(
//                    "SELECT * FROM article WHERE title LIKE ?")) {
//                stmt.setString(1, word + "%");
//                try (ResultSet resultSet = stmt.executeQuery()) {
//                    while (resultSet.next()) {
//                        articleList.add(new Article(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(5),resultSet.getTimestamp(4).toLocalDateTime()));
//                    }
//                }
//            }
//            return articleList;
//        }
//    }






    public static void main(String[] args) throws IOException, SQLException {

        ArticleDao articleDao = new ArticleDao();

        System.out.println(articleDao.getArticleById(2).toString());
//        System.out.println(articleDao.getAllArticles());
//        System.out.println(articleDao.findArticles("op"));
    }
}
