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

@WebServlet(urlPatterns = "/addnewarticle")
public class AddNewArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        try { int articleId = getLastInsertedId(DBConnectionUtils.getConnectionFromClasspath("connection.properties"));
        int authorId = Integer.parseInt(req.getParameter("authorId_AddArticle"));
        String content = req.getParameter("content_AddArticle");
        String title = req.getParameter("title_AddArticle");
        LocalDateTime dateTime = LocalDateTime.now();
        Article article = new Article(articleId,title,content,dateTime,authorId);
        articleDao.postNewArticle(article);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editSuccess.jsp");
        dispatcher.forward(req,resp);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }

}
