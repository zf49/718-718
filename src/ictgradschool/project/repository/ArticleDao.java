package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

public class ArticleDao {
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

    public static void main(String[] args) throws IOException, SQLException {
        ArticleDao articleDao = new ArticleDao();
        System.out.println(articleDao.getAllArticles());
    }
}
