package ictgradschool.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.controller.ArticleListController;
import ictgradschool.project.entity.Article;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/articles"})
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArticleListController articleListController = new ArticleListController();
        List<Article> articles = articleListController.getArticles();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(articles);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Article article = new Article();
        article.title = req.getParameter("title");
        article.content = req.getParameter("content");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(article);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);



    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        ArticleListController articleListController = new ArticleListController();
        List<Article> articles = articleListController.getArticles();
        for(Article a : articles){
            if(a.id == id){
                articles.remove(a);
            }
        }



    }
}


