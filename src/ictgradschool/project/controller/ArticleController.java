package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.ArticleDao;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.text.StringEscapeUtils;

public class ArticleController {

    ArticleDao articleDao = new ArticleDao();

    public Article getArticleById(HttpServletRequest req) throws IOException {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        return articleDao.getArticleById(articleId);
    }

    public Article editArticle(HttpServletRequest req) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        assert String.valueOf(user.getId()).equals(req.getParameter("authorId"));
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        String title = req.getParameter("articleTitle");
        String content = req.getParameter("articleContent");
        try {
            return articleDao.updateArticle(title, content, articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Article addArticle(HttpServletRequest req) throws IOException {
        Article article = null;
        String title = req.getParameter("articleTitle");
        String content = req.getParameter("articleContent");
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        content = StringEscapeUtils.escapeHtml4(content);
        try {
            article = articleDao.postNewArticle(title, content, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

}
