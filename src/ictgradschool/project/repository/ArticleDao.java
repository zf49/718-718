package ictgradschool.project.repository;

import ictgradschool.project.entity.Article;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ArticleDao {
    public List<Article> getAllArticles() throws IOException, SQLException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("database.properties")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(
                        "SELECT id, title, content, date_created FROM article")) {
                    System.out.println("mark 2");
                    while (resultSet.next()) {
                        System.out.println("mark 1");
                        System.out.println(resultSet.getInt(1));
                        // TODO: create article object, add to list, return
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, SQLException {
        ArticleDao articleDao = new ArticleDao();
        articleDao.getAllArticles();
    }
}
