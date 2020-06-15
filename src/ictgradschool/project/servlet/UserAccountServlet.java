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
import java.sql.SQLException;

@WebServlet(urlPatterns = "/account/*")
public class UserAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController userController = new UserController(new UserDao());
        User user = (User) req.getSession().getAttribute("user");
        user = userController.getUserDetails(user);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/myAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("delete"))
            this.doDelete(req, resp);
        else
            this.doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO update account information
    }

}
