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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(urlPatterns = "/editArticle")
public class EditArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         int articleId = Integer.parseInt(req.getParameter("session"));

        ArticleDao articleDao = new ArticleDao();
        Article article  = new Article();
        article = articleDao.getArticleById(articleId);
        req.setAttribute("article", article);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editArticle.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        int articleId = Integer.parseInt(req.getParameter("id"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        String content = req.getParameter("content");
        String title = req.getParameter("title");
        LocalDateTime dateTime = LocalDateTime.now();
        Article article = new Article(articleId,title,content,dateTime,authorId);

//        System.out.println("" + articleId + authorId + content+title+dateTime);
        articleDao.updateArticle(article);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editSuccess.jsp");
        dispatcher.forward(req,resp);
    }

}
