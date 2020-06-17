package ictgradschool.project.servlet;

import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.entity.Comment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reply-comment")
public class ReplyCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CommentListController controller = new CommentListController();
        Comment comment = controller.insertCommentToComment(req);
        resp.sendRedirect(req.getContextPath() + "/articles/" + comment.getArticleId());
    }
}
