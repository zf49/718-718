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

import java.util.Collections;
import java.util.List;


@WebServlet(urlPatterns = {"/articles"})
public class ShowAllArticleListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao a = new ArticleDao();
        List<Article> articleList = a.getAllArticles();
        req.setAttribute("a", articleList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/allArticles.jsp");
            dispatcher.forward(req,resp);
    }

}


