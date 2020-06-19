package ictgradschool.project.controller;

import ictgradschool.project.controller.exception.UnauthorizedException;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.ArticleDao;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.apache.commons.text.StringEscapeUtils;

public class ArticleController {

    ArticleDao articleDao = new ArticleDao();

    public Article getArticleById(HttpServletRequest req) throws IOException {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        return articleDao.getArticleById(articleId);
    }

    public Article editArticle(HttpServletRequest req) throws IOException, UnauthorizedException {
        User user = (User) req.getSession().getAttribute("user");
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        int authorId = articleDao.getArticleById(articleId).getAuthorId();
        if (user.getId() == authorId) {
            String title = req.getParameter("articleTitle");
            String content = req.getParameter("articleContent");
            title = StringEscapeUtils.escapeHtml4(title);
            content = StringEscapeUtils.escapeHtml4(content);
            return articleDao.updateArticle(title, content, articleId);
        } else {
            throw new UnauthorizedException();
        }
    }

    public Article addArticle(HttpServletRequest req) throws IOException {
        String title = req.getParameter("articleTitle");
        String content = req.getParameter("articleContent");
        User user = (User) req.getSession().getAttribute("user");
        title = StringEscapeUtils.escapeHtml4(title);
        content = StringEscapeUtils.escapeHtml4(content);
        return articleDao.postNewArticle(title, content, user.getId());
    }

    public void deleteArticle(int articleId, int userId) throws IOException, UnauthorizedException {
        int authorId = articleDao.getArticleById(articleId).getAuthorId();
        if (authorId == userId) {
            articleDao.deleteArticle(articleId);
        } else {
            throw new UnauthorizedException();
        }
    }

}
