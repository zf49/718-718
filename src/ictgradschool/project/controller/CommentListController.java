package ictgradschool.project.controller;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.CommentDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommentListController {
    private CommentDao commentDao;

    public CommentListController() {
        this.commentDao = new CommentDao();
    }

    public List<Comment> getCommentsByArticleId(int articleId) throws IOException, SQLException {
        return commentDao.getCommentsByArticleId(articleId);
    }

    public Comment getCommentById(int commentId) {
        try {
            return commentDao.getCommentById(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Comment postNewComment(Comment comment, int articleId) throws IOException, SQLException {
        return commentDao.postNewComment(comment, articleId);
    }

    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }

}
