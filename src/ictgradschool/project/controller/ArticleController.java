package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ArticleController {

    ArticleDao articleDao = new ArticleDao();

    public Article getArticleById(HttpServletRequest req) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        return articleDao.getArticleById(articleId);
    }

    public Article editArticle(HttpServletRequest req) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        Article article = articleDao.getArticleById(articleId);
        article.setTitle(req.getParameter("articleTitle"));
        article.setContent(req.getParameter("articleContent"));
        return articleDao.updateArticle(article);
    }

    public Article addArticle(HttpServletRequest req) {
        Article article = null;
        String title = req.getParameter("articleTitle");
        String content = req.getParameter("articleContent");
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        try {
            article = articleDao.postNewArticle(title, content, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

}
