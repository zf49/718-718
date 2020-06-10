package ictgradschool.project.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.CommentDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/articles/*")
public class ArticleServlet extends HttpServlet {
    private int articleId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        articleId = Integer.parseInt(pathInfo.split("/")[1]);
        // TODO get an article by ID

        // Codes below get all the comments for the article
        CommentListController commentListController = new CommentListController(new CommentDAO());
        List<Comment> comments = null;
        try {
            comments = commentListController.getCommentsByArticleId(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapperForComment = new ObjectMapper();
        String commentsJson = objectMapperForComment.writeValueAsString(comments);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(commentsJson);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Comment comment = getNewComment(req);
        CommentListController commentListController = new CommentListController(new CommentDAO());
        if (commentListController.postNewComment(comment, articleId)) {
            System.out.println("Comment successfully!");
        } else {
            System.out.println("Fail to comment.");
        }
        /*// For test
        comment.dateCreated = LocalDateTime.now();
        comment.id = 1234;
        comment.articleId = articleId;
        ObjectMapper objectMapperForComment = new ObjectMapper();
        String commentsJson = objectMapperForComment.writeValueAsString(comment);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(commentsJson);*/

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
