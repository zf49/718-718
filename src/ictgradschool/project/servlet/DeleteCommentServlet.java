package ictgradschool.project.servlet;

import ictgradschool.project.controller.CommentListController;
import ictgradschool.project.controller.exception.UnauthorizedException;
import ictgradschool.project.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/commentId")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("commentId"));
        CommentListController commentListController = new CommentListController();
        try {
            commentListController.deleteComment(user.getId(), id);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
