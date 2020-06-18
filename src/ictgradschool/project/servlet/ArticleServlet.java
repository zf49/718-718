package ictgradschool.project.servlet;

import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.ArticleDao;

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
        ArticleDao articleDao = new ArticleDao();
        Article article = articleDao.getArticleById(articleId);

        if (article == null) {
            req.getRequestDispatcher("/WEB-INF/not-exist.jsp").forward(req, resp);
        }
        else {
            CommentListController commentListController = new CommentListController();
            List<Comment> comments = commentListController.getCommentsByArticleId(articleId);

            req.setAttribute("article", article);
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/WEB-INF/article.jsp").forward(req, resp);
        }

    }

    /* Creates a new comment */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        CommentListController controller = new CommentListController();
        Comment comment = controller.addComment(req);
        resp.sendRedirect(req.getHeader("referer"));

    }

}
