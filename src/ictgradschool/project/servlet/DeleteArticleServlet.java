package ictgradschool.project.servlet;

import ictgradschool.project.controller.ArticleController;
import ictgradschool.project.controller.exception.UnauthorizedException;
import ictgradschool.project.entity.User;
import ictgradschool.project.servlet.exception.UserNotSignedInException;
import ictgradschool.project.util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/articleId")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArticleController controller = new ArticleController();
        try {
            User user = ServletUtil.getCurrentUser(req);
            int id = Integer.parseInt(req.getParameter("articleId"));
            try {
                controller.deleteArticle(id, user.getId());
                resp.sendRedirect(req.getHeader("referer"));
            } catch (UnauthorizedException e) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (UserNotSignedInException e) {
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        }
    }
}
