package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

public class ArticleController {

    ArticleDao articleDao = new ArticleDao();

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public Article updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

}
