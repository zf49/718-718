package ictgradschool.project.servlet;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/edit/*")
public class EditArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        req.setAttribute("article", articleDao.getArticleById(articleId));
        req.getRequestDispatcher("/WEB-INF/editArticle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        Article article = articleDao.getArticleById(articleId);
        article.title = req.getParameter("articleTitle");
        article.content = req.getParameter("articleContent");
        article = articleDao.updateArticle(article);
        resp.sendRedirect(req.getContextPath() + "articles/" + article.id);
    }

}
