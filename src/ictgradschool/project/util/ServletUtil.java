package ictgradschool.project.util;

import ictgradschool.project.entity.User;
import ictgradschool.project.servlet.exception.UserNotSignedInException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {
    public static void forward(HttpServletRequest req, HttpServletResponse resp, ServletContext context, String relativePath) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/" + relativePath);
        dispatcher.forward(req, resp);
    }

    public static User getCurrentUser(HttpServletRequest req) throws UserNotSignedInException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            throw new UserNotSignedInException();
        }
        return user;
    }
}
