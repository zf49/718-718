package ictgradschool.project.servlet;

import ictgradschool.project.controller.UserController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up-success")
public class SignUpSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sign-up-success.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController userController = new UserController(new UserDao());
        User user = (User) req.getSession().getAttribute("user");
        userController.addUserDetail(req, user);
        resp.sendRedirect(req.getContextPath() + "/home");
    }

}
