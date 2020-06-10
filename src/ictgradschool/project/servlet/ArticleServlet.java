package ictgradschool.project.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.controller.ArticleListController;
import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.ArticleDao;
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
import java.time.ZoneOffset;
import java.util.List;

@WebServlet(urlPatterns = "/articles/*")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO get an article by ID

        // Codes below get all the comments for the article
        String pathInfo = req.getPathInfo();
        int articleId = Integer.parseInt(pathInfo.substring(1));
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO post a new article

        // Codes below get all the comments for the article
        Comment comment = getNewComment(req);
        comment.id = 1234;
        comment.dateCreated = LocalDateTime.now();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(comment);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

    private Comment getNewComment(HttpServletRequest req) throws JsonProcessingException {
        // TODO get an article by ID

        // Codes below get all the comments for the article
        StringBuilder sb = new StringBuilder();
        String line = null;
        try{
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception ignored) {

        }
        return new ObjectMapper().readValue(sb.toString(), Comment.class);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("commentId"));
        ArticleListController articleListController = new ArticleListController(new ArticleDao());
        List<Article> articles = null;
        try {
            articles = articleListController.getArticles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Article a : articles){
            if(a.id == id){
                articles.remove(a);
            }
        }
    }
}
