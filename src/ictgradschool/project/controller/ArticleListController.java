package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.ArticleDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArticleListController {
    private ArticleDao articleDao;

    public ArticleListController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> getArticles() throws IOException, SQLException {
        return articleDao.getAllArticles();
    }

    public Article postNewArticle(Article article) throws IOException, SQLException {
        return articleDao.postNewArticle(article);
    }

}
