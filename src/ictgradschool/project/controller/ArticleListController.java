package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleListController {

    public List<Article> getArticles() {
        List<Article> articleList = new ArrayList<>();
        Article article = new Article();
        article.id = 1;
        article.title = "Allways wanting food";
        article.content = "I love cats i am one wake up scratch humans leg for food";
        article.date = 1591672745;
        articleList.add(article);
        return articleList;
    }
}
