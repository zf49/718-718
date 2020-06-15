package ictgradschool.project.servlet;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;
import ictgradschool.project.repository.DaoUtil;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static ictgradschool.project.repository.DaoUtil.getLastInsertedId;

@WebServlet(urlPatterns = "/new-article")
public class NewArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/new-article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        Article article = new Article();
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
        resp.sendRedirect(req.getContextPath() + "/articles/" + article.id);
    }

}
