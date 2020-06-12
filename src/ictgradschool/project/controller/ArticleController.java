package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

import java.io.IOException;
import java.sql.SQLException;

import java.time.LocalDateTime;

public class ArticleController {

    ArticleDao articleDao = new ArticleDao();

    public Article getArticleById(int id) throws SQLException, IOException {
        return articleDao.getArticleById(id);
    }

    public Article updateArticle(Article article) throws SQLException, IOException {
        return articleDao.updateArticle(article);
    }
}
