package ictgradschool.project.servlet;

import ictgradschool.project.controller.AuthController;
import ictgradschool.project.controller.exception.IncorrectPasswordException;
import ictgradschool.project.controller.exception.NoSuchUsernameException;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.forward(req, resp, getServletContext(), "sign-in.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AuthController authController = new AuthController(new UserDao());
        try {
            User user = authController.signIn(username, password);

            req.getSession().setMaxInactiveInterval(ServletUtil.kSessionTimeInterval);
            req.getSession().setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (NoSuchUsernameException | IncorrectPasswordException e) {
            resp.sendRedirect(req.getContextPath() + "/sign-in-failure");
        }
    }
}
