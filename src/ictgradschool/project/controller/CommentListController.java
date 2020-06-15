package ictgradschool.project.controller;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.CommentDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommentListController {
    private CommentDao commentDAO;

    public CommentListController() {
        this.commentDAO = new CommentDao();
    }

    public List<Comment> getCommentsByArticleId(int articleId) throws IOException, SQLException {
        return commentDAO.getCommentsByArticleId(articleId);
    }

    public Comment postNewComment(Comment comment, int articleId) throws IOException, SQLException {
        return commentDAO.postNewComment(comment, articleId);
    }

    public void deleteComment(int commentId) {
        commentDAO.deleteComment(commentId);
    }

}
