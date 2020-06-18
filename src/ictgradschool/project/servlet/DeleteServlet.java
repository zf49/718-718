package ictgradschool.project.servlet;

import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.controller.UserController;
import ictgradschool.project.controller.exception.UnauthorizedException;
import ictgradschool.project.repository.ArticleDao;
import ictgradschool.project.repository.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/delete/*")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("mark 2");
        String pathInfo = req.getPathInfo();
        if (pathInfo.contains("commentId")) {
            int id = Integer.parseInt(req.getParameter("commentId"));
            CommentListController commentListController = new CommentListController();
            commentListController.deleteComment(id);
            resp.sendRedirect(req.getHeader("referer"));
        }
        else if (pathInfo.contains("articleId")){
            int id = Integer.parseInt(req.getParameter("articleId"));
            ArticleDao articleDao = new ArticleDao();
            articleDao.deleteArticle(id);
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
