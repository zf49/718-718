package ictgradschool.project.servlet;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(urlPatterns = "/edit/*")
public class EditArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Article article  = new Article();
        if (req.getPathInfo().contains("articleId")) {
            int articleId = Integer.parseInt(req.getParameter("articleId"));
            ArticleDao articleDao = new ArticleDao();
            article = articleDao.getArticleById(articleId);
        }
        req.setAttribute("article", article);
        req.getRequestDispatcher("/WEB-INF/edit-article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        Article article  = null;
        if (articleId == 0) {                               // Add a new article
            article = new Article();
            article.title = req.getParameter("articleTitle");
            article.content = req.getParameter("articleContent");
            article.authorId = Integer.parseInt(req.getParameter("authorId"));
            try {
                article = articleDao.postNewArticle(article);
            } catch (SQLException e) {
                resp.setStatus(500);
                e.printStackTrace();
                throw new ServletException("Database access error!", e);
            }
            resp.sendRedirect("/articles/" + article.id);
        } else {                                              // Update an article
            article = articleDao.getArticleById(articleId);
            article.title = req.getParameter("articleTitle");
            article.content = req.getParameter("articleContent");
            article = articleDao.updateArticle(article);
            resp.sendRedirect("/articles/" + article.id);
        }
    }

}
