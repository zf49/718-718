package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
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
                        "SELECT id, title, content, date_created FROM article")) {
                    List<Article> articles = new LinkedList<>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String content = resultSet.getString(3);
                        // TODO: uncomment this after we have date in data
//                        LocalDateTime dateCreated = resultSet.getDate(4).toInstant()
//                                .atZone(ZoneId.systemDefault())
//                                .toLocalDateTime();
                        Article article = new Article(id, title, content, null);
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
    private Article getArticleFromResultSet(ResultSet rs) throws SQLException {
        return new Article(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                ///TODO renewdate
                rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
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
    public void updateArticle(Article article) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("UPDATE article SET title=?,content=?,date_created=? WHERE id=?;")){
            ps.setString(1, article.title);
            ps.setString(2, article.content);
            //TODO
            ps.setInt(4,article.id);




        }
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
