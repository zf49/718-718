package ictgradschool.project.controller;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.CommentDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommentListController {
    private CommentDAO commentDAO;

    public CommentListController(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getCommentsByArticleId(int articleId) throws IOException, SQLException {
        return commentDAO.getCommentsForArticle(articleId);
    }
}
