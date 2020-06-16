package ictgradschool.project.servlet;

import ictgradschool.project.controller.AvatarController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.AvatarDao;
import ictgradschool.project.repository.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/avatar-select")
public class AvatarPickServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AvatarController avatarController = getAvatarController(req);
        User user;
        String avatarName = req.getParameter("avatarName");
        user = avatarController.updateAvatar(avatarName);
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("./avatar");
    }

    private AvatarController getAvatarController(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        AvatarDao avatarDao = new AvatarDao();
        return new AvatarController(user, userDao, avatarDao);
    }
}
