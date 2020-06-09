package ictgradschool.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;

import javax.persistence.criteria.CriteriaBuilder;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (isArticleUrl(pathInfo)) {
            int articleId = Integer.parseInt(pathInfo.substring(1));
            ArticleController articleController = new ArticleController();
            Article article = articleController.getArticleById(articleId);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(article);


            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } else {
            int articleId = Integer.parseInt(pathInfo.substring(1, 2));

            CommentListController commentListController = new CommentListController();
            List<Comment> comments = commentListController.getCommentListByArticleId(articleId);

            ObjectMapper objectMapperForComment = new ObjectMapper();
            String commentsJson = objectMapperForComment.writeValueAsString(comments);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(commentsJson);
        }

    }

    private boolean isArticleUrl(String pathInfo) {
        return !pathInfo.contains("comments");

    }
}
