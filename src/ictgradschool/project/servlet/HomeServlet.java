package ictgradschool.project.servlet;

import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;
import ictgradschool.project.util.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/home", ""})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletUtil.forward(req, resp, getServletContext(), "home.jsp");
        ArticleDao a = new ArticleDao();
        List<Article> articleList = a.getAllArticles();
        req.setAttribute("a", articleList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(req,resp);
    }
}
