package ictgradschool.project.servlet;

import ictgradschool.project.controller.CommentListController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/commentId")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: check authorization
        int id = Integer.parseInt(req.getParameter("commentId"));
        CommentListController commentListController = new CommentListController();
        commentListController.deleteComment(id);
        resp.sendRedirect(req.getHeader("referer"));
    }
}
