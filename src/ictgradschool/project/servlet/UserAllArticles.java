package ictgradschool.project.servlet;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.User;
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
import java.util.List;


@WebServlet(urlPatterns = {"/userArticles"})
public class UserAllArticles extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ArticleDao a = new ArticleDao();
            User user = (User) req.getSession().getAttribute("user");
            int authorId = user.getId();
            List<Article> articleList = a.getArticleByUserId(authorId);
            req.setAttribute("a", articleList);
            req.setAttribute("authorId", authorId);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/userArticle.jsp");
            dispatcher.forward(req,resp);

    }
}
