package ictgradschool.project.servlet;

import ictgradschool.project.controller.UserController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.forward(req, resp, getServletContext(), "/WEB-INF/sign-up.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        UserController userController = new UserController(new UserDao());
        User user = userController.signUp(username, password, confirmPassword);
        req.getSession().setAttribute("user", user);
        // TODO: add context path
        resp.sendRedirect(user != null ? "./home" : "./sign-up-failure");
    }
}
