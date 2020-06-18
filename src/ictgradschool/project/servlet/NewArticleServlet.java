package ictgradschool.project.servlet;

import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/new-article")
public class NewArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/new-article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArticleController controller = new ArticleController();
        Article article = controller.addArticle(req);
        resp.sendRedirect(req.getContextPath() + "/articles/" + article.getId());
    }

}
