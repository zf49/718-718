package ictgradschool.project.servlet;

import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/edit/*")
public class EditArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleController articleController = new ArticleController();
        req.setAttribute("article", articleController.getArticleById(req));
        req.getRequestDispatcher("/WEB-INF/edit-article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArticleController controller = new ArticleController();
        Article article = controller.editArticle(req);
        resp.sendRedirect(req.getContextPath() + "/articles/" + article.getId());
    }

}
