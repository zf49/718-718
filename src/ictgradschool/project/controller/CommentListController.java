package ictgradschool.project.controller;

import ictgradschool.project.controller.exception.UnauthorizedException;
import ictgradschool.project.entity.Comment;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.CommentDao;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class CommentListController {

    private CommentDao commentDao;

    public CommentListController() {
        this.commentDao = new CommentDao();
    }

    public List<Comment> getCommentsByArticleId(int articleId) throws IOException {
        List<Comment> comments = commentDao.getCommentsByArticleId(articleId);
        return Comment.flatten(comments);
    }

    public void deleteComment(int userId, int commentId, int articleAuthorId) throws IOException, UnauthorizedException {
        int authorId = commentDao.getCommentById(commentId).getAuthorId();
        if (authorId == userId || articleAuthorId == userId) {
            commentDao.deleteCommentById(commentId);
        } else {
            throw new UnauthorizedException();
        }
    }


    public Comment addComment(HttpServletRequest req) throws IOException {
        int articleId = Integer.parseInt(req.getPathInfo().split("/")[1]);
        String content = req.getParameter("commentContent");
        content = StringEscapeUtils.escapeHtml4(content);
        int parentId = Integer.parseInt(req.getParameter("parentId"));
        User user = (User) req.getSession().getAttribute("user");
        return commentDao.insertNewComment(content, user.getId(), articleId, parentId);
    }

}
