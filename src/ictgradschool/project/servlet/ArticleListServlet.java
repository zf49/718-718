package ictgradschool.project.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.ArticleListController;
import ictgradschool.project.entity.Article;
import ictgradschool.project.repository.ArticleDao;

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


@WebServlet(urlPatterns = {"/articles"})
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArticleListController articleListController = new ArticleListController(new ArticleDao());
        List<Article> articles = null;
        try {
            articles = articleListController.getArticles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(articles);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Article articleInput = getArticle(req);
        articleInput.id = 1234;
        articleInput.dateCreated = LocalDateTime.now();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(articleInput);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        
    }

    private Article getArticle(HttpServletRequest req) throws JsonProcessingException {
         StringBuffer sb = new StringBuffer();
         String line = null;
         try{
             BufferedReader reader = req.getReader();
             while((line = reader.readLine()) != null)
                 sb.append(line);
         } catch (Exception e) {}

         return new ObjectMapper().readValue(sb.toString(), Article.class);

    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
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


