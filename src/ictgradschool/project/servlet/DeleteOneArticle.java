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

@WebServlet(urlPatterns = "/delete")
public class DeleteOneArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        ArticleDao articleDao = new ArticleDao();
        articleDao.deleteOneArticle(articleId);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/edit-success.jsp");
        dispatcher.forward(req,resp);
    }
}
