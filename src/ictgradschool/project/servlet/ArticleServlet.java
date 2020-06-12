package ictgradschool.project.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = "/articles/*")
public class ArticleServlet extends HttpServlet {

    private int articleId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String pathInfo = req.getPathInfo();
        articleId = Integer.parseInt(pathInfo.split("/")[1]);
        ArticleController articleController = new ArticleController();
        Article article;
        try {
            article = articleController.getArticleById(articleId);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }

        // Codes below get all the comments for the article
        CommentListController commentListController = new CommentListController(new CommentDAO());
        List<Comment> comments;
        try {
            comments = commentListController.getCommentsByArticleId(articleId);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }

        req.setAttribute("article", article);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("WEB-INF/article.jsp").forward(req, resp);

    }

    /* Updates the article */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        String line;
        try{
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception ignored) {
        }
        Article article = new ObjectMapper().readValue(sb.toString(), Article.class);
        article.id = articleId;
        article.dateCreated = LocalDateTime.now();
        ArticleController articleController = new ArticleController();
        try {
            article = articleController.updateArticle(article);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }

        ObjectMapper objectMapperForComment = new ObjectMapper();
        String articleJson = objectMapperForComment.writeValueAsString(article);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(articleJson);

    }

    /* Creates a new comment */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Comment comment = getNewComment(req);
        CommentListController commentListController = new CommentListController(new CommentDAO());
        try {
            comment = commentListController.postNewComment(comment, articleId);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }

        ObjectMapper objectMapperForComment = new ObjectMapper();
        String commentsJson = objectMapperForComment.writeValueAsString(comment);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(commentsJson);

    }

    /* Reads the author ID and the content of a new comment */
    private Comment getNewComment(HttpServletRequest req) throws JsonProcessingException {

        StringBuilder sb = new StringBuilder();
        String line;
        try{
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception ignored) {
        }
        return new ObjectMapper().readValue(sb.toString(), Comment.class);

    }

    /* Deletes a comment */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        int id = Integer.parseInt(req.getParameter("commentId"));
        CommentListController commentListController = new CommentListController(new CommentDAO());
        if (commentListController.deleteComment(id)) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Fail to delete.");
        }

    }

}
