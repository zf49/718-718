package ictgradschool.project.servlet;

import ictgradschool.project.repository.ArticleDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/articleId")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: check authorization
        int id = Integer.parseInt(req.getParameter("articleId"));
        ArticleDao articleDao = new ArticleDao();
        articleDao.deleteArticle(id);
        resp.sendRedirect(req.getHeader("referer"));
    }
}
