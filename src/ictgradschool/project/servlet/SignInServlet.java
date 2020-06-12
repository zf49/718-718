package ictgradschool.project.servlet;

import ictgradschool.project.controller.AuthController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp, "/WEB-INF/sign-in.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AuthController authController = new AuthController(new UserDao());
        User user = authController.signIn(username, password);
        req.setAttribute("user", user);
        forward(req, resp, user == null ? "/WEB-INF/sign-in-failure.jsp" : "/WEB-INF/sign-in-success.jsp");
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        ServletUtil.forward(req, resp, getServletContext(), path);
    }
}
