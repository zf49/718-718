package ictgradschool.project.controller;

import ictgradschool.project.entity.Article;

public class ArticleController {
    public Article getArticleById(int id) {

        Article article = new Article();
        article.id = 1;
        article.title = "Allways wanting food";
        article.content = "I love cats i am one wake up scratch humans leg for food";
        article.date = 1591672745;

        return article;
    }
}
