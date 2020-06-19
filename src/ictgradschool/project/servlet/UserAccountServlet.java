package ictgradschool.project.servlet;

import ictgradschool.project.controller.UserController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.servlet.exception.UserNotSignedInException;
import ictgradschool.project.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/account")
public class UserAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController userController = new UserController(new UserDao());
        try {
            User user = ServletUtil.getCurrentUser(req);
            user = userController.getUserDetails(user);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/my-account.jsp").forward(req, resp);
        } catch (UserNotSignedInException e) {
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController userController = new UserController(new UserDao());
        try {
            User user = ServletUtil.getCurrentUser(req);
            String form = req.getParameter("submitButton");
            if (form.contains("additional")) {
                userController.changeUserDetail(req, user);
            } else {
                user = userController.changeUser(req, user.getId());
            }
            if (user != null) {
                req.getSession().setAttribute("user", user);
            }
            resp.sendRedirect(req.getContextPath() + "/account");
        } catch (UserNotSignedInException e) {
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        }
    }

}
