package ictgradschool.project.servlet;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reply-comment")
public class ReplyCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDao commentDao = new CommentDao();
        String content = req.getParameter("replyContent");
        int parentId = Integer.parseInt(req.getParameter("parentId"));
        User user = (User) req.getSession().getAttribute("user");
        Comment comment = commentDao.insertCommentToComment2(content, user.getId(), parentId);
        resp.sendRedirect(req.getContextPath() + "/articles/" + comment.getArticleId());
    }
}
