package ictgradschool.project.servlet;

import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.ArticleDao;
import ictgradschool.project.repository.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/articles/*")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int articleId = Integer.parseInt(req.getPathInfo().split("/")[1]);
        Article article = getArticleById(articleId, resp);
        if (article == null)
            // TODO notice users that the article does not exist
            resp.sendRedirect("/home");
        else {
            req.setAttribute("article", article);
            req.setAttribute("comments", getCommentsByArticleId(articleId, resp));
            req.setAttribute("author", getUserById(article.getAuthorId(), resp));
            req.getRequestDispatcher("/WEB-INF/article.jsp").forward(req, resp);
        }

    }

    private User getUserById(int id, HttpServletResponse resp) throws IOException, ServletException {
        UserDao userDao = new UserDao();
//        try {
            return userDao.getUserById(id);
//        } catch (SQLException e) {
//            resp.setStatus(500);
//            e.printStackTrace();
//            throw new ServletException("Database access error!", e);
//        }
    }

    private Article getArticleById(int id, HttpServletResponse resp) throws IOException, ServletException {
        ArticleController articleController = new ArticleController();
        try {
            return articleController.getArticleById(id);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }
    }

    // Gets all the comments for the article
    private List<Comment> getCommentsByArticleId(int id, HttpServletResponse resp) throws IOException, ServletException {
        CommentListController commentListController = new CommentListController();
        try {
            return commentListController.getCommentsByArticleId(id);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }
    }

    /* Creates a new comment */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String pathInfo = req.getPathInfo();
        if (pathInfo.contains("delete"))
            this.doDelete(req, resp);
        else {
            int articleId = Integer.parseInt(pathInfo.split("/")[1]);
            Comment comment = new Comment();
            comment.content = req.getParameter("commentContent");
            comment.authorId = Integer.parseInt(req.getParameter("userId"));
            CommentListController commentListController = new CommentListController();
            try {
                comment = commentListController.postNewComment(comment, articleId);
            } catch (SQLException e) {
                resp.setStatus(500);
                e.printStackTrace();
                throw new ServletException("Database access error!", e);
            }
            resp.sendRedirect(req.getContextPath() + "/articles/" + articleId);
        }

    }

    /* Deletes a comment */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo.contains("commentId")) {
            int articleId = Integer.parseInt(pathInfo.split("/")[1]);
            int id = Integer.parseInt(req.getParameter("commentId"));
            CommentListController commentListController = new CommentListController();
            commentListController.deleteComment(id);
            resp.sendRedirect(req.getContextPath() + "/articles/" + articleId);
        } else if (pathInfo.contains("articleId")){
            int id = Integer.parseInt(req.getParameter("articleId"));
            ArticleDao articleDao = new ArticleDao();
            articleDao.deleteOneArticle(id);
            String lastPage = req.getHeader("Referer");
            System.out.println("last page url: " + lastPage);
            resp.sendRedirect("/home");
        }
    }

}
