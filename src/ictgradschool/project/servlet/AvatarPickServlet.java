package ictgradschool.project.servlet;

import ictgradschool.project.controller.AvatarController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.AvatarDao;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.servlet.exception.UserNotSignedInException;

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
        try {
            String avatarName = req.getParameter("avatarName");
            User user = getAvatarController(req).updateAvatar(avatarName);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("./avatar");
        } catch (UserNotSignedInException e) {
            resp.sendRedirect("./sign-in");
        }
    }

    private AvatarController getAvatarController(HttpServletRequest req) throws UserNotSignedInException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            throw new UserNotSignedInException();
        }
        UserDao userDao = new UserDao();
        AvatarDao avatarDao = new AvatarDao();
        return new AvatarController(user, userDao, avatarDao);
    }
}
