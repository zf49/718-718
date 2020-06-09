package ictgradschool.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/articles/*")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int articleId = Integer.parseInt(pathInfo.substring(1));
        ArticleController articleController = new ArticleController();
        Article article = articleController.getArticleById(articleId);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(article);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
