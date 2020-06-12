package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

import java.sql.SQLException;

public class ArticleController {
    ArticleDao articleDao = new ArticleDao();

    public Article getArticleById(int id) throws SQLException {
        return articleDao.getArticleById(id);
    }

    public Article updateArticle(Article article) throws SQLException {
        return articleDao.updateArticle(article);
    }
}
