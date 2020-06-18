package ictgradschool.project.servlet;

import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.ArticleDao;
import ictgradschool.project.repository.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/user/*"})
public class UserHomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao a = new ArticleDao();
        UserDao userDao = new UserDao();
        int userId = Integer.parseInt(req.getPathInfo().split("/")[1]);
        List<Article> articleList = a.getArticleByUserId(userId);
        User user = userDao.getUserById(userId);

        req.setAttribute("articles", articleList);
        System.out.println(articleList.size());
        req.setAttribute("pageUser", user);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/my-homepage.jsp");
        dispatcher.forward(req, resp);
    }

}
