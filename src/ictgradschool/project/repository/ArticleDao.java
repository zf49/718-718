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
    private  Connection connection;

    public ArticleDao() {
        try {
            this.connection =  DBConnectionUtils.getConnectionFromClasspath("connection.properties");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getAllArticles() throws IOException, SQLException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("database.properties")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(
                        "SELECT id, title, content, date_created, author_id FROM article")) {
                    List<Article> articles = new LinkedList<>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String content = resultSet.getString(3);
                        LocalDateTime dateCreated = resultSet.getDate(4).toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime();
                        int authorId = resultSet.getInt(5);
                        Article article = new Article(id, title, content, dateCreated, authorId);
                        articles.add(article);
                    }
                    return articles;
                }
            }
        }
    }

    public List<Article> getArticleByUserId(int authorId) throws SQLException {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM article WHERE author_id = ?")) {
                   stmt.setInt(1, authorId);
                   List<Article> articleList = new ArrayList<>();
                   try(ResultSet rs = stmt.executeQuery()){
                       if(rs.next()){
                           articleList.add(getArticleFromResultSet(rs));
                           return articleList;
                       }else{
                           return null;
                       }
                   }
            }
    }

    public Article getArticleById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT title, content, date_created, author_id FROM article WHERE id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Article article = new Article();
                article.id = id;
                article.title = resultSet.getString(1);
                article.content = resultSet.getString(2);
                article.dateCreated = resultSet.getTimestamp(3).toLocalDateTime();
                //LocalDateTime dateCreated = resultSet.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                article.authorId = resultSet.getInt(4);
                return article;
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

    public boolean postNewArticle(Article article) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO article (id,title, content,date_created) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, article.id);
            ps.setString(2, article.title);
            ps.setString(3, article.content);
            //TODO create_date

            int rows = ps.executeUpdate();
            if(rows == 0){
                return false;
            }else {
                return true;
            }
        }
    }

    public Article updateArticle(Article article) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("UPDATE article SET title=?,content=?,date_created=?,author_id=? WHERE id=?;")){
            ps.setString(1, article.title);
            ps.setString(2, article.content);
            ps.setDate(3, (java.sql.Date) Date.from(article.dateCreated.atZone(ZoneId.systemDefault()).toInstant()));
            ps.setInt(4, article.authorId);
            ps.setInt(5,article.id);
            ps.executeQuery();
        }
        return getArticleById(article.id);

    }


    public boolean deleteOneArticle(int articleId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM article WHERE id = ?")) {
            stmt.setInt(1, articleId);
            int row = stmt.executeUpdate();
            if(row == 1){
                return true;
            }else {
                return false;
            }
        }
    }

    public void deleteUserAllArticle(int authorId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM article WHERE article.author_id = ?")) {
            stmt.setInt(1, authorId);
        }
    }






    public static void main(String[] args) throws IOException, SQLException {
        ArticleDao articleDao = new ArticleDao();
        System.out.println(articleDao.getAllArticles());
    }
}
