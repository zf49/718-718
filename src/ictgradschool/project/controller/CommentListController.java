package ictgradschool.project.controller;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.repository.CommentDao;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommentListController {

    private CommentDao commentDao;

    public CommentListController() {
        this.commentDao = new CommentDao();
    }

    public List<Comment> getCommentsByArticleId(int articleId) throws IOException {
        List<Comment> comments = commentDao.getCommentsByArticleId2(articleId);
        return Comment.flatten(comments);
    }

    public Comment getCommentById(int commentId) {
        try {
            return commentDao.getCommentById(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comment postNewComment(HttpServletRequest req) {
        Comment comment = new Comment();
        String pathInfo = req.getPathInfo();
        int articleId = Integer.parseInt(pathInfo.split("/")[1]);
        comment.setContent(req.getParameter("commentContent"));
        comment.setAuthorId(Integer.parseInt(req.getParameter("userId")));
        try {
            return commentDao.postNewComment(comment, articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteComment(int commentId) throws IOException {
        commentDao.deleteCommentById2(commentId);
    }

}
