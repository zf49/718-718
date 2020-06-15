package ictgradschool.project.servlet;

import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.controller.UserController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;
import ictgradschool.project.repository.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/*")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String lastPage = req.getHeader("Referer"); // TODO how to use it?
        if (pathInfo.contains("commentId")) {
            int id = Integer.parseInt(req.getParameter("commentId"));
            CommentListController commentListController = new CommentListController();
            int articleId = commentListController.getCommentById(id).getArticleId();
            commentListController.deleteComment(id);
            resp.sendRedirect(req.getContextPath() + "/articles/" + articleId);
        } else if (pathInfo.contains("articleId")){
            int id = Integer.parseInt(req.getParameter("articleId"));
            ArticleDao articleDao = new ArticleDao();
            articleDao.deleteOneArticle(id);
            resp.sendRedirect("/articles");
        } else if (pathInfo.contains("userId")) {
            UserController userController = new UserController(new UserDao());
            int userId = Integer.parseInt(req.getParameter("userId"));
            userController.deleteUser(userId);
            resp.sendRedirect("/home");
        }
    }
}
