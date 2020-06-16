package ictgradschool.project.servlet;

import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/articles/*")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int articleId = Integer.parseInt(req.getPathInfo().split("/")[1]);
        ArticleController articleController = new ArticleController();
        Article article = articleController.getArticleById(articleId);

        if (article == null) {
            // TODO notice users that the article does not exist
            resp.sendRedirect("/home");
        }
        else {
            CommentListController commentListController = new CommentListController();
            List<Comment> comments = commentListController.getCommentsByArticleId(articleId);
            UserDao userDao = new UserDao();
            User author = userDao.getUserById(article.getAuthorId());

            req.setAttribute("article", article);
            req.setAttribute("comments", comments);
            req.setAttribute("author", author);
            req.getRequestDispatcher("/WEB-INF/article.jsp").forward(req, resp);
        }

    }

    /* Creates a new comment */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        CommentListController controller = new CommentListController();
        Comment comment = controller.postNewComment(req);
        resp.sendRedirect(req.getContextPath() + "/articles/" + comment.getArticleId());

    }

}
