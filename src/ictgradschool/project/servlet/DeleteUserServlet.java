package ictgradschool.project.servlet;

import ictgradschool.project.controller.UserController;
import ictgradschool.project.controller.exception.UnauthorizedException;
import ictgradschool.project.repository.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/userId")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        UserController userController = new UserController(new UserDao());
        try {
            userController.deleteUser(req, id);
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

